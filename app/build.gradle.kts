import com.android.build.api.dsl.Packaging

plugins {
    alias(libs.plugins.agp)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.worldonetop.ourry"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.worldonetop.ourry"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    kotlin {
        jvmToolchain(8)
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.bundles.compose)
    implementation(platform(libs.compose.bom))

    implementation(libs.bundles.coroutine)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.paging)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
    implementation(libs.security.crypto)
    implementation(libs.bundles.lifecycle)
    implementation(libs.glide)

}