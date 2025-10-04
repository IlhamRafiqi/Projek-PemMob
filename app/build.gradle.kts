plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    signingConfigs {
        getByName("debug") {
            storeFile = file("D:\\MyKeyStore\\KeyStore")
            storePassword = "Indonesia_9"
            keyAlias = "key-Sejenak"
            keyPassword = "Indonesia_9"
        }
    }
    namespace = "com.example.sejenakapps"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.sejenakapps"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        signingConfig = signingConfigs.getByName("debug")
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15" // ✅ cocok untuk Kotlin 1.9.25
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("com.google.android.material:material:1.12.0")

    // ✅ Jetpack Compose
    implementation(platform("androidx.compose:compose-bom:2024.09.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // ✅ Firebase (pakai BOM biar versi otomatis sinkron)
    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation ("com.google.firebase:firebase-analytics-ktx")
    implementation ("com.google.android.gms:play-services-auth:21.4.0")

    testImplementation("junit:junit:4.13.2")
}
