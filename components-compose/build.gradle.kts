plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("maven-publish")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "dev.trindadedev.easyui.components.compose"
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
    
    buildFeatures {
        compose = true
    }
    
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "18"
}

dependencies {
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    
    // compose
    implementation(platform("androidx.compose:compose-bom:2024.09.01"))
    implementation("androidx.compose.material3:material3")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.activity:activity-compose:1.9.1")
    implementation("androidx.activity:activity:1.9.1")
    implementation("androidx.navigation:navigation-compose:2.7.7")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "dev.trindadedev.easyui.components.compose"
            artifactId = "components"
            version = "0.0.1"

            from(components.findByName("release") ?: components["java"])

            pom {
                name.set("Easy UI Components Compose")
                description.set("Some components for Jetpack Compose")
                url.set("https://github.com/trindadev13/easy-ui")
                licenses {
                    license {
                        name.set("GPL 3.0 License")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.pt-br.html")
                    }
                }
                developers {
                    developer {
                        id.set("trindadedev13")
                        name.set("Aquiles Trindade")
                        email.set("devsuay@example.com")
                    }
                }
            }
        }
    }
}
