# Most Viewed Articles App

## Overview
This application displays the most viewed articles using two screens: a list screen and a detail screen. It leverages modern Android development practices and technologies:
- **MVVM Pattern**: Ensures a clear separation between the UI and business logic.
- **Kotlin Coroutines & Flows**: Manages asynchronous operations efficiently.
- **Jetpack Compose**: Builds a modern, declarative UI.
- **Hilt**: Simplifies dependency injection.
- **Unit Tests**: Ensures high code quality and reliability.

## Prerequisites
- Android Studio 4.1 or higher
- API Key from [New York Times Developer](https://developer.nytimes.com/get-started)

## Setup

### Clone the Repository
Clone the repository to your local machine:
```sh
git clone https://github.com/your-repo-name.git
```

### API Key Configuration
1. Create a new file called `apikeys.properties` in the root directory of your Android project.
2. Open `apikeys.properties` and add your API key:
    ```properties
    A_K="your API key value"
    ```
3. To obtain an API key:
    - Go to the [New York Times Developer](https://developer.nytimes.com/get-started) page.
    - Sign up or log in to your account.
    - Navigate to the "Apps" section and create a new app.
    - Copy the provided API key and paste it into the `apikeys.properties` file.

## Building and Running the App
1. Open the project in Android Studio.
2. Sync the project with Gradle files.
3. Build and run the app on an emulator or physical device.

## Project Structure
- **core**: Contains core functionalities like dispatchers, network error handling, etc.
- **data**: Handles data sources, models, and repositories.
- **ui**: Contains UI-related code including composables, viewmodels, and state management.
- **domain**: Contains use cases and business logic.
