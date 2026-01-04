plugins {
    id("com.android.library") version "8.13.0"
    id("org.jetbrains.kotlin.android") version "2.2.20"
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.20"
    id("maven-publish")
}

android {
    namespace = "com.github.mohammadjoshaghani.composescreen"
    compileSdk = 36

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    //noinspection UseTomlInstead
    implementation("androidx.compose.material3:material3:1.4.0")
    // viewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.10.0")
    // stickyHeader in BaseScreenLazyList
    implementation("androidx.compose.foundation:foundation:1.10.0")
    // BackHandler
    implementation("androidx.activity:activity-compose:1.12.1")
    // window size class
    api("androidx.compose.material3:material3-window-size-class-android:1.4.0")

    implementation(libs.androidx.compose.material.icons.extended)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
