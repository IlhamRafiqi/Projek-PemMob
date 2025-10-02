// Top-level Gradle build file

plugins {
    id("com.android.application") version "8.13.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
    alias(libs.plugins.google.gms.google.services) apply false
}
