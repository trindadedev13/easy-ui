pluginManagement {
  repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
  }
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "trindade"

// libs
include(":components")
include(":components-compose")
include(":filepicker")

// samples
include(":sample-components")
include(":sample-components-compose")