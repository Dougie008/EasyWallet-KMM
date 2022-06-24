plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.easy.wallet.android"
        minSdk = 25
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-beta02"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))
    implementation(project(":shared"))

    implementation("androidx.compose.ui:ui:1.2.0-rc02")
    implementation("androidx.compose.material3:material3:1.0.0-alpha13")
    implementation("androidx.compose.material3:material3-window-size-class:1.0.0-alpha13")
    implementation("io.coil-kt:coil-compose:2.1.0")
    implementation("androidx.activity:activity-ktx:1.4.0")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("com.google.android.material:material:1.6.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")
}