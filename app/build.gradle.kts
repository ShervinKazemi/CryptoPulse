plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.cryptopulse"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.cryptopulse"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
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
    implementation(libs.androidx.ui.text.google.fonts)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Koin For dependency Injection =>
    implementation(libs.cokoin)
    implementation(libs.cokoin.android.viewmodel)
    implementation(libs.cokoin.android.navigation)

    // Retrofit For Connect to Api  =>
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // System Ui Controller =>
    implementation(libs.accompanist.systemuicontroller)

    // lottie Animations for add animations =>
    implementation(libs.lottie.compose)

    // flow for state management =>
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.reactive)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // ViewPager for onboarding Screen =>
    implementation(libs.foundation.pager)

    // Coil For Image Loading =>
    implementation(libs.coil.compose)

    // SplashScreen =>
    implementation(libs.androidx.core.splashscreen)

    // okHttp client =>
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

}