
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("maven-publish")
}

group = "dev.trindadedev.easyui.filepicker"

android {
    namespace = "dev.trindadedev.easyui.filepicker"
    compileSdk = 34
    
    defaultConfig {
        minSdk = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    
    kotlinOptions {
        jvmTarget = "18"
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "18"
}

dependencies {
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.trindadedev13"
            artifactId = "filepicker"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
