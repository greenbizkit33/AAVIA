plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.nathanhaze.aavia"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nathanhaze.aavia"
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

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.3")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.compose.runtime:runtime-android:1.6.8")
    implementation("androidx.compose.foundation:foundation-layout-android:1.6.8")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    //Compose
    implementation("androidx.compose.foundation:foundation")
    // or only import the main APIs for the underlying toolkit systems,
    // such as input and measurement/layout
    implementation("androidx.compose.ui:ui")

    // Android Studio Preview support
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    implementation("androidx.compose.foundation:foundation:1.6.8")

    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.ui:ui-framework:0.1.0-dev10")

    implementation ("com.google.code.gson:gson:2.11.0")

//
//    implementation("androidx.activity:activity-compose:1.9.0")
//    implementation("androidx.appcompat:appcompat:1.7.0")
//    implementation("androidx.compose.animation:animation-core:compose_version")
//    implementation("androidx.compose.animation:animation:compose_version")
//    implementation("androidx.compose.foundation:foundation-layout:compose_version")
//    implementation("androidx.compose.foundation:foundation:compose_version")
//    implementation("androidx.compose.material:material-icons-core:compose_version")
//    implementation("androidx.compose.material:material-icons-extended:compose_version")
//    implementation("androidx.compose.material:material:compose_version")
    implementation("androidx.compose.material:material:compose_version")
//    implementation("androidx.compose.runtime:runtime-livedata:compose_version")
//    implementation("androidx.compose.runtime:runtime-rxjava2:compose_version")
//    implementation("androidx.compose.ui:ui-geometry:compose_version")
//    implementation("androidx.compose.ui:ui-graphics:compose_version")
//    implementation("androidx.compose.ui:ui-tooling:compose_version")
//    implementation("androidx.compose.ui:ui-tooling:compose_version")
//    implementation("androidx.compose.ui:ui:compose_version")
//    implementation("androidx.core:core-ktx:1.10.1")
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
//    implementation("com.google.android.material:material:1.9.0")
//    implementation("androidx.compose.ui:ui-viewbinding:compose_version")
//    implementation("androidx.compose.ui:ui-text:compose_version")
//    implementation("androidx.compose.ui:ui-util:compose_version")
//    implementation("androidx.compose.ui:ui:compose_version")

    //Compose Constraintlayout
 //   implementation 'androidx.constraintlayout:constraintlayout-compose:1.0.1'
}