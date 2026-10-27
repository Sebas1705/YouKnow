# YouKnow: a race hidden in a base ViewModel

How adding tests to an Android trivia app exposed a coverage job that ran nothing, then a flaky CI failure whose real cause was a constructor-ordering race in the MVI base class.

## Context

[YouKnow](https://github.com/Sebas1705/YouKnow) is an Android trivia app (Kotlin, Jetpack Compose, multi-module Clean Architecture with Hilt). Every screen's ViewModel extends a shared `MVIBaseViewModel`, which owns the state flow and exposes an `onInit()` hook for the first load. Unit test depth was low, so I set out to raise it.

## Problem

In [#54](https://github.com/Sebas1705/YouKnow/pull/54) I added 124 tests: domain mappers and use cases, plus Robolectric tests for the five game ViewModels (Quiz, Mystery Number, Daily Challenge, Word Pass, Families). The ViewModels run their loads on the real `Dispatchers.IO`, so the tests await the `StateFlow` with Turbine, which uses wall-clock timeouts.

Two problems surfaced.

**The coverage job was a no-op.** `JacocoConventionPlugin` looked up `testDebugUnitTest` with `findByName` when the plugin was applied, before AGP had registered the task. The lookup returned `null`, every `jacocoTestReport` had no execution data and was `SKIPPED`, and the "Unit Tests + Coverage" CI job had been running zero tests.

**Main went red right after the merge.** The same tests that passed on the PR failed on `main`: `DailyChallengeViewModelTest` reported `No value produced in 3s` ([run 40221308373](https://github.com/Sebas1705/YouKnow/actions/runs/40221308373)).

## Approach

**Fixing the plugin.** The tasks are now matched lazily, so the dependency is resolved when the task graph is built, and the execution data is read from its known path. A file tree instead of a single file keeps a module with no tests skipped rather than failed:

```kotlin
val unitTestTasks = tasks.matching { it.name == "testDebugUnitTest" }
val compileTasks = tasks.matching {
    it.name == "compileDebugKotlin" || it.name == "compileDebugJavaWithJavac"
}
val execFiles = fileTree(layout.buildDirectory.dir("jacoco")) {
    include("testDebugUnitTest.exec")
}
```

I also added a merged report across modules and a coverage table in the CI job summary.

**The flaky tests: the wrong first guess.** A timeout on a loaded runner looks like slowness, so my first commit raised the Turbine timeouts to 30 seconds. CI failed again, this time with `No value produced in 30s`, and in two tests instead of one. Waiting ten times longer had changed nothing, and that was the useful signal: the value was never going to arrive. Something was killing the load, not delaying it.

**Root cause.** The test report artifact of the failed run held a suppressed `NullPointerException` at `DailyChallengeViewModel.kt:58`, the line that reads the injected `dailyChallengeUsesCases`. A constructor-injected `val` should never be null, unless it is read before it is assigned. The base class was:

```kotlin
abstract class MVIBaseViewModel<S, I> : ViewModel() {
    init { onInit() }   // runs inside the superclass constructor
}
```

In Kotlin (as on the JVM) the superclass constructor, including its `init` blocks, runs *before* the subclass assigns its constructor properties. `DailyChallengeViewModel.onInit()` launched `loadChallenge()` on `Dispatchers.IO`. Usually the constructor finished first; on a 2-vCPU runner the IO thread sometimes won, read a still-null field, and the coroutine died. The state stayed in `LOADING` forever, so whichever test was waiting timed out, which is why the failure moved between tests. `HomeViewModel` and `SurveyViewModel` also load from `onInit()`, so the app itself could hit the same race.

**The fix ([#55](https://github.com/Sebas1705/YouKnow/pull/55)).** One line: `init { execute { onInit() } }`. `execute` launches on `Dispatchers.Main`, which (unlike `Main.immediate`) always dispatches, so `onInit()` runs once construction is complete, still exactly once and still on Main. The trade-off is explicit in the PR: the hook now runs on the next main-thread turn instead of synchronously.

The regression test, `MVIBaseViewModelTest`, mirrors the feature ViewModels with a subclass that reads a constructor dependency in `onInit()`. With a `StandardTestDispatcher` as Main, it asserts that nothing has run right after construction, then that `onInit()` saw the injected value and ran exactly once. One of its two tests fails against the old base class. The 30-second timeouts were reverted to Turbine's defaults, since they had only hidden the symptom for longer.

## Result

- The coverage pipeline runs tests again. Merged line coverage went from 1.24 % (measured with the fixed plugin) to 9.50 % (1,341 of 14,119 lines). The denominator includes all the untested Compose UI, so the absolute number is low by construction; the point was a real, visible baseline.
- Locally, `:core:common` and `:feature:game` passed three times in a row with `--rerun-tasks` on 2 workers (104 tests each run), and `coverageUnitTestAll` passed with 201 tests. The [Validation Pipeline on main](https://github.com/Sebas1705/YouKnow/actions/runs/40567949201) went green after the merge.
- The [CHANGELOG](https://github.com/Sebas1705/YouKnow/blob/main/CHANGELOG.md) records both fixes for users of the app, not just for CI.

## Lessons

- **If a longer timeout doesn't change the outcome, it isn't a timeout problem.** Raising it was a cheap experiment, and its failure was the most informative result of the day.
- **Read the artifacts, not just the log line.** The assertion said "no value"; the suppressed exception in the report said why.
- **Never call overridable code from a base constructor.** It is a classic JVM pitfall, and coroutines turn it from a deterministic crash into a race that only shows up on a slower machine.
- **Flaky tests can be real bugs.** This one was a startup race in production code that the new tests happened to be the first to notice.
