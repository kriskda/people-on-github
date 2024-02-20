apply(from = "../../android.common.gradle")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "io.github.repository"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data:network"))
    implementation(project(":data:database"))

    implementation(libs.hiltDaggerCore)
    ksp(libs.hiltDaggerCompiler)

    implementation(libs.roomRuntime)
    implementation(libs.roomKtx)
    implementation(libs.roomPaging)
    ksp(libs.roomCompiler)

    implementation(libs.pagingRuntime)
    implementation(libs.pagingCompose)

    testImplementation(libs.testJunit)
    testImplementation(libs.testMockitoKotlin)
    testImplementation(libs.testCoroutines)
    testImplementation(libs.testRobolectric)
}