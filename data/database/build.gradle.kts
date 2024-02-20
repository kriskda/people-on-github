apply(from = "../../android.common.gradle")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "io.github.database"
}

dependencies {
    implementation(libs.hiltAndroid)
    implementation(libs.hiltDaggerCore)
    ksp(libs.hiltDaggerCompiler)

    implementation(libs.roomRuntime)
    implementation(libs.roomKtx)
    implementation(libs.roomPaging)
    ksp(libs.roomCompiler)

    testImplementation(libs.testJunit)
    testImplementation(libs.testCoroutines)
    testImplementation(libs.testRobolectric)
}