plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")         // annotation-processing
    id("dagger.hilt.android.plugin")        // plugin de Hilt
}

android {
    namespace = "com.example.masivocapital"
    compileSdk = 34              // 36 es preview; usa 34 estable a menos que tengas el SDK preview

    defaultConfig {
        applicationId = "com.example.masivocapital"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
}

dependencies {
    // ─── Core + UI ────────────────────────────────────────────────────────────────
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.activity:activity-ktx:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // ─── Room ─────────────────────────────────────────────
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    // ─── Hilt ───────────────────────────────────────
    val hiltVersion = "2.48"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-compiler:$hiltVersion")

    // ─── Retrofit ───────────────────────────────────────
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.12")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")
    implementation("androidx.work:work-runtime-ktx:2.9.0")


    implementation("androidx.hilt:hilt-work:1.1.0")
    kapt("androidx.hilt:hilt-compiler:1.1.0")

    // ─── Tests ───────────────────────────────────────────────────────────────────
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

}
