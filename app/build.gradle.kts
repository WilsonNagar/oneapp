plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ktlint)
}

android {
    namespace = "com.oneapp.app"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.oneapp.app"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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

    flavorDimensions += "environment"
    productFlavors {
        create("qa") {
            dimension = "environment"
            applicationIdSuffix = ".qa"
            buildConfigField("String", "BASE_URL", "\"https://qa.example.com/api/\"")
            buildConfigField("String", "ENV", "\"QA\"")
        }
        create("prod") {
            dimension = "environment"
            buildConfigField("String", "BASE_URL", "\"https://prod.example.com/api/\"")
            buildConfigField("String", "ENV", "\"PROD\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Module dependencies
    implementation(project(":common"))
    implementation(project(":network"))
    implementation(project(":db"))
    implementation(project(":common-feature-location"))

    // Compose BOM
    implementation(platform(libs.composeBom))
    implementation(libs.composeUi)
    implementation(libs.composeUiGraphics)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.composeMaterial3)
    implementation(libs.composeActivity)

    // Hilt
    implementation(libs.hiltAndroid)
    kapt(libs.hiltCompiler)
    implementation(libs.hiltNavigationCompose)

    // Navigation
    implementation(libs.navigationCompose)

    // Lifecycle
    implementation(libs.lifecycleRuntimeKtx)
    implementation(libs.lifecycleViewmodelCompose)

    // Coroutines
    implementation(libs.coroutinesCore)
    implementation(libs.coroutinesAndroid)

    // AndroidX
    implementation(libs.coreKtx)
    implementation(libs.appcompat)

    // WorkManager
    implementation(libs.workmanager)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutinesTest)
    androidTestImplementation(libs.junitExt)
    androidTestImplementation(platform(libs.composeBom))
    androidTestImplementation(libs.composeUiTest)
    androidTestImplementation(libs.espressoCore)
    debugImplementation(libs.composeUiTooling)
    debugImplementation(libs.composeUiTestManifest)
}

