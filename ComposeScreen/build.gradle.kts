plugins {
    id("com.android.library") version "8.12.0"
    id("org.jetbrains.kotlin.android") version "2.2.0"
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.0"
    id("maven-publish")
}

android {
    namespace = "com.github.mohammadjoshaghani.composescreen"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    //noinspection UseTomlInstead
    implementation("androidx.compose.material3:material3:1.3.2")
    // viewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.2")
    // stickyHeader in BaseScreenLazyList
    implementation("androidx.compose.foundation:foundation:1.8.3")
    // BackHandler
    implementation("androidx.activity:activity-compose:1.10.1")
    // window size class
    implementation("androidx.compose.material3:material3-window-size-class-android:1.3.2")
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
