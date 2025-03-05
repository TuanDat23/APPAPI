plugins {
    alias(libs.plugins.androidApplication)
}

android {
<<<<<<< HEAD
    namespace = "com.example.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.app"
=======
    namespace = "com.example.appapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.appapi"
>>>>>>> 72dcb02832af2e83e1025972998d4d91b3c8cc5d
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
}
<<<<<<< HEAD
dependencies {
=======

dependencies {

>>>>>>> 72dcb02832af2e83e1025972998d4d91b3c8cc5d
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
<<<<<<< HEAD
    implementation("com.android.volley:volley:1.2.1") // Thêm Volley đúng cách
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
=======
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
>>>>>>> 72dcb02832af2e83e1025972998d4d91b3c8cc5d
