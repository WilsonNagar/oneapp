# OneApp

A multi-module Android application built with Kotlin, Jetpack Compose, Hilt, MVVM, and Clean Architecture principles.

## Project Structure

This project follows a multi-module architecture with the following modules:

- **:app** - Android application module with QA and PROD flavors
- **:common** - Shared UI components, utilities, and common code
- **:common-feature-location** - Location tracking feature module (Clean Architecture)
- **:network** - Network layer with Retrofit, OkHttp, and Moshi
- **:db** - Room database module with entities and DAOs

## Architecture

The project follows **Clean Architecture** principles with clear separation of concerns:

- **UI Layer**: Jetpack Compose screens and ViewModels
- **Domain Layer**: Use cases and repository interfaces
- **Data Layer**: Repository implementations, data sources, and services

**MVVM Pattern**: ViewModels expose StateFlow for UI state management.

**Dependency Injection**: Hilt is used throughout the project for dependency injection.

## Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **DI**: Hilt
- **Architecture**: MVVM + Clean Architecture
- **Database**: Room
- **Networking**: Retrofit + OkHttp + Moshi
- **Navigation**: Navigation Compose
- **Coroutines**: kotlinx.coroutines with StateFlow
- **Image Loading**: Coil
- **Logging**: Timber
- **Background Tasks**: WorkManager

## Setup

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- JDK 17 or later
- Android SDK with API level 34

### Importing the Project

1. Open Android Studio
2. Select "Open an Existing Project"
3. Navigate to the project root directory
4. Wait for Gradle sync to complete

### Building the Project

The project uses Gradle Kotlin DSL and a version catalog (`gradle/libs.versions.toml`) for dependency management.

#### Build Flavors

The app module includes two build flavors:

- **QA**: 
  - Application ID: `com.oneapp.app.qa`
  - Base URL: `https://qa.example.com/api/`
  - Environment: `QA`

- **PROD**:
  - Application ID: `com.oneapp.app`
  - Base URL: `https://prod.example.com/api/`
  - Environment: `PROD`

#### Building QA Flavor

```bash
./gradlew assembleQaDebug
```

Or in Android Studio:
1. Go to Build → Select Build Variant
2. Choose `qaDebug` or `qaRelease`

#### Building PROD Flavor

```bash
./gradlew assembleProdDebug
```

Or in Android Studio:
1. Go to Build → Select Build Variant
2. Choose `prodDebug` or `prodRelease`

### Running the App

1. Select the desired build variant (QA or PROD)
2. Connect an Android device or start an emulator (API 24+)
3. Click Run or press `Shift+F10`

### Running Tests

#### Unit Tests

```bash
./gradlew test
```

#### Instrumented Tests (UI Tests)

```bash
./gradlew connectedAndroidTest
```

Note: UI tests may be disabled in CI if flaky. See `.github/workflows/ci.yml` for details.

## Code Quality Tools

### ktlint

Code formatting is enforced using ktlint. Configuration is in `.ktlint.yml`.

```bash
./gradlew ktlintCheck
./gradlew ktlintFormat
```

### detekt

Static code analysis is performed using detekt. Configuration is in `detekt.yml`.

```bash
./gradlew detekt
```

## Module Details

### :app

Main application module containing:
- `MainActivity` with Navigation Compose setup
- Hilt application class
- Build flavors (QA/PROD)
- AndroidManifest with permissions and service declarations
- Sample WorkManager worker

### :common

Shared module providing:
- Compose UI components (AppTheme, LoadingState, ErrorCard, Greeting)
- Utility classes (EncryptedSharedPreferences)
- Common dependencies (Timber, Coil)

### :common-feature-location

Location tracking feature module with Clean Architecture:

- **Domain**: `LocationRepository` interface, use cases (GetLastLocation, StartLocationTracking, StopLocationTracking)
- **Data**: `LocationRepositoryImpl`, `LocationService` (foreground service), `FakeLocationProvider` (for testing)
- **UI**: `LocationScreen` composable, `LocationViewModel`

**Note**: Location tracking uses a fake provider for now. Replace with FusedLocationProvider when ready to integrate Google Play Services.

### :network

Network module providing:
- Retrofit setup with OkHttp and Moshi
- `ApiService` interface with sample `/health` endpoint
- `FakeHealthApi` for offline testing
- Hilt `NetworkModule` with dependency injection

### :db

Room database module providing:
- `AppDatabase` with migration support
- `LocationEntity` and `LocationDao`
- Hilt `DatabaseModule` for dependency injection

## Assumptions

1. **Location Services**: Currently uses a fake location provider. Replace with Google Play Services FusedLocationProvider when ready.
2. **Network Calls**: Uses fake implementations to allow builds and tests to run offline.
3. **Migrations**: Database uses `fallbackToDestructiveMigration()` for now. Replace with proper migrations as schema evolves.
4. **WorkManager**: Sample worker is provided but not fully integrated with Hilt AssistedInject.

## Included Extras

- ✅ ktlint configuration
- ✅ detekt configuration
- ✅ Timber logging setup
- ✅ GitHub Actions CI workflow
- ✅ Coil image loader (dependency included)
- ✅ WorkManager dependency and sample worker
- ✅ EncryptedSharedPreferences utility skeleton
- ✅ ProGuard rules placeholders

## CI/CD

GitHub Actions workflow (`.github/workflows/ci.yml`) runs on push and PR:
- Builds the project
- Runs unit tests
- Runs ktlint checks
- Runs detekt (with continue-on-error)

UI tests are commented out by default as they can be flaky in CI.

## Package Structure

- `com.oneapp.app` - Application module
- `com.oneapp.common` - Common module
- `com.oneapp.location` - Location feature module
- `com.oneapp.network` - Network module
- `com.oneapp.db` - Database module

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Changelog

See [CHANGELOG.md](CHANGELOG.md) for a list of changes.

