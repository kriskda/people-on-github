apply(from = "../android.common.gradle")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "io.github.domain"
}

dependencies {
    implementation(libs.hiltDaggerCore)

    implementation(libs.pagingRuntime)
    implementation(libs.pagingCompose)

    testImplementation(libs.testJunit)
    testImplementation(libs.testMockitoKotlin)
    testImplementation(libs.testCoroutines)
}