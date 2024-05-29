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

include(":views")
include(":preferences")
include(":filepicker")
include(":github_api")
include(":mercadopago")
include(":inapp")
include(":utils")
