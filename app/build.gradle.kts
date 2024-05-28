import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.googleDevtoolsKsp)
    alias(libs.plugins.daggerHiltAndroid)
}

android {
    namespace = "com.renad.nytpulse"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.renad.nytpulse"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

       //load the values from .properties file
        val keystoreFile = project.rootProject.file("apikeys.properties")
        val properties = Properties()
        properties.load(keystoreFile.inputStream())

        //return empty key in case something goes wrong
        val ak = properties.getProperty("A_K") ?: ""
        buildConfigField(type = "String", name = "A_K", value = ak)
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.moshi.kotlin)
    implementation(libs.converter.moshi)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.scalars)
    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.kotlinx.coroutines.test)

    // Navigation
    implementation(libs.androidx.navigation.compose)
    // hilt
    implementation(libs.hilt.android)
    // declare the implementation for the compiler
    ksp(libs.hilt.android.compiler)
    // Navigation with Hilt
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.lottie)
    testImplementation(libs.mockk)
    testImplementation(libs.mockk.android)
    testImplementation(libs.glide.compose)
    testImplementation(libs.androidx.arch.core.testing)
    testImplementation(libs.fixture)
}

ksp {
    arg("dagger.hilt.android.internal.disableAndroidSuperclassValidation", "true")
    arg("room.schemaLocation", project.layout.buildDirectory.dir("schemas").get().asFile.absolutePath)
}

