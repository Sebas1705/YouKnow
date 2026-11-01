// The email migration, run against the Firestore emulator with the Admin SDK like in production.
// `npm test` runs test files one at a time: they share the emulator's data.
import assert from "node:assert/strict";
import { after, before, beforeEach, describe, test } from "node:test";
import { deleteApp, initializeApp } from "firebase-admin/app";
import { getFirestore } from "firebase-admin/firestore";
import { challengeProblems, migrate, userProblems } from "./migrations/remove-user-emails.mjs";

const profile = (over = {}) => ({
  provider: 1, nickName: "alice", photoUrl: null, groupId: "g", points: 5, credits: 10, logged: true, ...over,
});
const round = (over = {}) => ({
  date: "2026-09-30",
  questions: [{ question: "q", answers: ["a", "b"], correctAnswer: "a", category: "ANY", difficulty: "MEDIUM", quizType: "MULTIPLE" }],
  ...over,
});

let app;
let db;

before(() => {
  app = initializeApp({ projectId: "demo-youknow" }, "migrations-test");
  db = getFirestore(app);
});
after(() => deleteApp(app));
beforeEach(async () => {
  await db.recursiveDelete(db.collection("users"));
  await db.recursiveDelete(db.collection("dailyChallenges"));
});

describe("remove-user-emails migration", () => {
  test("a dry run reports the emails and changes nothing", async () => {
    await db.doc("users/alice").set(profile({ email: "a@example.com" }));

    const report = await migrate(db);

    assert.equal(report.withEmail, 1);
    assert.equal(report.removed, 0);
    assert.equal((await db.doc("users/alice").get()).get("email"), "a@example.com");
  });

  test("applying removes only the email, and running it again does nothing", async () => {
    await db.doc("users/alice").set(profile({ email: "a@example.com" }));
    await db.doc("users/bob").set(profile({ nickName: "bob" }));

    const first = await migrate(db, { apply: true });
    const second = await migrate(db, { apply: true });

    assert.deepEqual([first.users, first.withEmail, first.removed], [2, 1, 1]);
    assert.deepEqual([second.users, second.withEmail, second.removed], [2, 0, 0]);
    assert.deepEqual((await db.doc("users/alice").get()).data(), profile());
    assert.deepEqual((await db.doc("users/bob").get()).data(), profile({ nickName: "bob" }));
  });

  test("goes through more documents than fit in one page", async () => {
    const seed = db.bulkWriter();
    for (let i = 0; i < 405; i++) seed.set(db.doc(`users/u${i}`), profile({ email: `${i}@example.com` }));
    await seed.close();

    const report = await migrate(db, { apply: true });

    assert.deepEqual([report.users, report.removed], [405, 405]);
    assert.equal((await migrate(db)).withEmail, 0);
  });

  test("lists malformed documents without touching them, and never exposes an email", async () => {
    await db.doc("users/bad").set(profile({ logged: "x", email: "bad@example.com" }));
    await db.doc("dailyChallenges/2030-01-01").set(round({ questions: [1, 2] }));
    await db.doc("dailyChallenges/2026-09-30").set(round());

    const report = await migrate(db, { apply: true });

    assert.deepEqual(report.malformedUsers, ["bad"]);
    assert.deepEqual(report.malformedChallenges, ["2030-01-01"]);
    assert.equal(report.challenges, 2);
    assert.equal((await db.doc("users/bad").get()).get("logged"), "x");
    assert.ok(!JSON.stringify(report).includes("@"));
  });
});

describe("shape checks of the migration", () => {
  test("a user the app can read has no problems", () => {
    assert.deepEqual(userProblems(profile()), []);
    assert.deepEqual(userProblems(profile({ email: "a@example.com" })), []);
  });

  test("flags each field that would break the mapper", () => {
    assert.deepEqual(userProblems(profile({ logged: "x" })), ["logged"]);
    assert.deepEqual(userProblems(profile({ provider: "x" })), ["provider"]);
    assert.deepEqual(userProblems(profile({ points: 2147483648 })), ["points"]);
    assert.deepEqual(userProblems(profile({ credits: -1 })), ["credits"]);
    assert.deepEqual(userProblems(profile({ photoUrl: 1, groupId: {} })), ["photoUrl", "groupId"]);
    assert.deepEqual(userProblems({}), ["nickName", "points", "credits"]);
  });

  test("a round is playable only if every question is", () => {
    const question = round().questions[0];
    assert.deepEqual(challengeProblems(round()), []);
    assert.deepEqual(challengeProblems(round({ questions: [] })), ["questions"]);
    assert.deepEqual(challengeProblems(round({ questions: "x" })), ["questions"]);
    assert.deepEqual(challengeProblems(round({ questions: [1, 2] })), ["questions"]);
    assert.deepEqual(challengeProblems(round({ questions: [{}] })), ["questions"]);
    assert.deepEqual(challengeProblems(round({ questions: [question, { ...question, correctAnswer: "z" }] })), ["questions"]);
    assert.deepEqual(challengeProblems(round({ questions: [{ ...question, answers: ["a", 1] }] })), ["questions"]);
  });
});
