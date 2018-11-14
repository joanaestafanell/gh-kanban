# GH-Kanban
This is a sample app demonstrating GitHub API data consuming using Retrofit. Allows the creation of Kanban boards for any of a user's public projects. The app is built on Android Architecture Components and uses Data Binding.

## Features
    - MVVM Architecture pattern
    - ROOM to persist data
    - LiveData and Data binding to display data
    - Retrofit with GSON lib to consume GitHub REST API
    - Hardcoded GitHub user
    - Basic Unit tests and Integration tests implemented

## Future Improvements
    - Improve backstack navigation
    - Explore menu:
        - Implement pagination and endless scroll RecyclerView when loading remote data.
        - Disable projects that already have a kanban board.
        - On project click, control when a project doesn't have issues to display and prevent kanban creation | inform user.
    - Kanban menu:
        - Format issue date
    - Error control
    - Extend unit and integration tests