# AI Agent Guidelines & Project Conventions

This document outlines the rules and guidelines for AI agents interacting with this project. The primary goal is to maintain consistency in architecture, code style, and technology stack.

## 1. Modular Architecture

The project employs a clean, multi-module architecture. All new code or modifications must adhere to this layer separation.

- **`:app`**: The main application module. It hosts the top-level Application class, ties together all the feature modules, and handles the final app packaging. It should remain lean.

- **`:core`**: Cross-cutting modules containing code shared across the entire application.
    - `:core:common`: Contains common utilities, extension functions, and helpers that are framework-agnostic.
    - `:core:designsystem`: Implements the application's visual identity. It includes Jetpack Compose `Theme`, colors, typography, and custom, stateless UI components.
    - `:core:resources`: Centralizes Android resources like strings, drawables, and other assets. This helps with localization and consistency.
    - `:core:ui`: Contains reusable UI-related logic and base components, such as base ViewModel classes or complex, stateful composables.

- **`:data`**: Responsible for all data handling and access logic.
    - Implements the repository interfaces defined in the `:domain` layer.
    - Contains data source implementations (e.g., Room DAOs, Retrofit services, Firestore clients, Couchbase access).
    - Uses **Kotlinx Serialization** for all JSON parsing and object serialization.
    - Handles mapping between network/database models (Entities) and domain models.

- **`:domain`**: The core business logic layer of the application.
    - **This must be a pure Kotlin module** with no dependencies on the Android framework (`android.*`).
    - Contains business models (plain Kotlin classes), repository interfaces, and Use Cases (or Interactors).
    - Use Cases encapsulate a single piece of business logic and should be the primary entry point to the domain layer from the ViewModels.

- **`:feature`**: Self-contained modules representing a specific feature or screen.
    - Each feature module is independent and follows the **MVVM (Model-View-ViewModel)** pattern.
    - **Jetpack Compose** is used exclusively for building the UI.
    - **ViewModels** orchestrate UI state and handle user events.
    - **Hilt** is used for dependency injection.
    - Navigation between features is handled via **Compose Navigation**, with routes defined as constants.

## 2. Dependency Injection

- **Hilt** is the standard dependency injection framework.
- Annotate Android framework classes with `@AndroidEntryPoint` (e.g., Activities, Fragments, Services).
- ViewModels must be annotated with `@HiltViewModel` and inject their dependencies via the constructor (`@Inject constructor(...)`).
- Provide dependencies using Hilt modules (`@Module`, `@Provides`). Install modules in the appropriate Hilt component (e.g., `@InstallIn(SingletonComponent::class)` for application-wide singletons).
- Always favor constructor injection over field injection.

## 3. Presentation Layer (UI)

- The UI is built exclusively with **Jetpack Compose**. Avoid using XML-based layouts.
- Use **Compose Navigation** for all navigation logic. Navigation graphs should be defined within the feature modules that own the screens.
- Reusable, stateless UI components should be placed in the `:core:designsystem` module.
- UI state should be managed by a **ViewModel**. Expose state to the UI using `StateFlow` and collect it as `State` in your Composables (`collectAsStateWithLifecycle()`).
- User events from the UI should be channeled to the ViewModel through public functions, which in turn trigger the relevant business logic (Use Cases).

## 4. Data Management

- Data sources are strictly separated into their own modules within the `:data` layer (e.g., `:data:room`, `:data:retrofit`). This enforces separation of concerns.
- **Room** is used for local database operations. Entities (`@Entity`) and Data Access Objects (`@Dao`) must be defined within the corresponding Room module.
- **Retrofit** is used for all network calls. API service interfaces are defined here.
- For simple key-value storage and user preferences, use **Jetpack DataStore**.
- The communication between the `:data` and `:domain` layers is contract-based, using the repository interfaces defined in `:domain`. The `:data` layer provides the concrete implementations.

## 5. Code Style & Syntax

- Strictly follow the official [Kotlin Style Guide](https://kotlinlang.org/docs/coding-conventions.html) and [Android Kotlin style guide](https://developer.android.com/kotlin/style-guide).
- Use **Kotlin Coroutines** and `Flow` for all asynchronous operations. Avoid callbacks or other legacy async patterns.
- Leverage the existing `build-logic` with **convention plugins**. When creating a new module, apply the correct convention plugin (e.g., `buildlogic.feature` for a new feature module) to ensure consistent configuration.
- Manage all dependencies centrally via the `libs.versions.toml` file. Always use the generated type-safe accessors (`libs.hilt.android`, `libs.androidx.compose.ui`, etc.) when declaring dependencies. Do not hardcode versions in build files.

## 6. Commenting Style

- All public classes, methods, and complex properties must be documented using **KDoc**.
- The KDoc block should provide a clear and concise summary of the element's purpose.
- In addition to standard KDoc tags (`@param`, `@return`, etc.), the following project-specific tags are mandatory:
    - `@since [version]`: The version number when the feature was introduced (e.g., `0.1.0`).
    - `@author [Name] [dd/mm/yyyy]`: The author's name and the date of creation or significant modification.

- **Example:**
  ```kotlin
  /**
   * A brief description of what this class or function does.
   *
   * @param name A description of this parameter.
   * @return A description of the return value.
   *
   * @since 0.1.0
   * @author YourName 02/10/2025
   */
  ```

By adhering to these guidelines, any contribution will uphold the quality, consistency, and architectural integrity of the codebase.
