# People on github
"People on GitHub" is an Android app with two main features: a user list for easy searching of GitHub profiles and a user detail screen for in-depth information.



https://github.com/kriskda/people-on-github/assets/2589087/ce219553-8c47-4ff0-906b-a6daa230e8be



## Authentication
The GitHub API necessitates an authentication token. Currently, from the user interface perspective, there isn't an option to input this token directly. Instead, the token is pre-configured within the `GitHubFeedService`.

## Components
The app is structured into several Gradle modules, each serving a distinct purpose:
- `app`: This module forms the presentation layer of the application, consisting of Compose screens and ViewModels that orchestrate the user interface.
- `domain`: While it could be omitted in this simplified example, the domain module is typically where the business logic resides, beginning with the use cases. It's designed to encapsulate the core operations and rules of the application.
- `data`: This module is dedicated to data provision for the app and is divided into three sub-modules:
  - `network`: Handles external data feeds,
  - `database`: Manages a local database for caching purposes,
  - `repository`: Combines data from both the network and database, then emits refined domain-specific data.
This modular structure enhances maintainability, scalability, and clarity by clearly separating concerns within the application.  

## Unit tests
The provided example includes a selection of unit tests. However, it's important to note that not all classes have been covered with tests in this instance.
