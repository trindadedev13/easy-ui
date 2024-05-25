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
<<<<<<< HEAD
include(":github_api")
=======
include(":github_api")
include(":mercadopago")
>>>>>>> b0b89c3 (MercadoPago api add)
