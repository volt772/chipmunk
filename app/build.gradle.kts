plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("org.jlleitschuh.gradle.ktlint")
}

android {
    compileSdk = Version.compileSdkVersion

    defaultConfig {
        applicationId = "com.apx6.chipmunk"
        minSdk = Version.minSdkVersion
        targetSdk = Version.targetSdkVersion
        versionCode = Version.versionCode
        versionName = Version.versionName

//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.apx6.chipmunk.CustomTestRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
                arguments["room.incremental"] = "true"
                arguments["room.expandProjection"] = "true"
            }
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file("../../chipmunk_keystore/chipmunk_deploy.jks")
            storePassword = System.getenv("CHIPMUNK_KEYSTORE_PW")
            keyAlias = "chipmunk_key"
            keyPassword = System.getenv("CHPIMUNK_KEY_PW")
        }
    }

    bundle {
        language {
            // Specifies that the app bundle should not support
            // configuration APKs for language resources. These
            // resources are instead packaged with each base and
            // dynamic feature APK.
            enableSplit = false
        }
        density {
            // This property is set to true by default.
            enableSplit = true
        }
        abi {
            // This property is set to true by default.
            enableSplit = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        val options = this
        options.jvmTarget = "11"
    }

    /* ONLY Hilt / Coroutine Test*/
    packagingOptions {
        resources {
            excludes += "/META-INF/AL2.0"
            excludes += "/META-INF/LGPL2.1"
        }
    }

    buildFeatures {
        dataBinding = true
    }
}

configurations.all {
    exclude(group = Configs.group, module = Configs.module)
}

dependencies {

    /* Android*/
    implementation(Android.appcompat)
    implementation(Android.activityKtx)
    implementation(Android.coreKtx)
    implementation(Android.constraintLayout)
    implementation(Android.swipeRefreshLayout)
    implementation(Android.splashScreen)

    /* LifeCycle*/
    implementation(Lifecycle.viewModel)
    implementation(Lifecycle.liveData)
    implementation(Lifecycle.runtimeKtx)

    /* Coroutine*/
    implementation(Coroutines.core)
    implementation(Coroutines.android)

    /* Material*/
    implementation(Material.core)

    /* Glide*/
    implementation(Glide.core)

    /* Joda time*/
    implementation(Joda.core)

    /* Hilt*/
    implementation(Hilt.dagger)
    kapt(Hilt.compiler)

    /* Badge Count*/
    implementation(Badge.shortcutBadge)

    /* Firebase*/
    implementation(platform(Firebase.bom))
    implementation(Firebase.analytics)
    implementation(Firebase.core)
    implementation(Firebase.messaging)
    implementation(Firebase.config)
    implementation(Firebase.configKtx)
    implementation(Firebase.analyticsKtx)
    implementation(Firebase.crashlytics)
    implementation(Firebase.volley)

    implementation(
        group = Kakao.Sdk.group,
        name = Kakao.Sdk.name,
        version = Kakao.Sdk.version
    )
    implementation(Kakao.user)

    androidTestImplementation(Test.jUnit)
    androidTestImplementation(Test.workTest)
    androidTestImplementation(Test.coreTest)
    androidTestImplementation(Test.coreKtx)
    androidTestImplementation(Test.espresso)
    androidTestImplementation(Test.espressoContrib)
    androidTestImplementation(Test.espressoIntents)
    androidTestImplementation(Test.jUnitExt)
    androidTestImplementation(Test.jUnitKtx)
    androidTestImplementation(Test.rules)
    androidTestImplementation(Test.runner)
    androidTestImplementation(Test.fragment)
    androidTestImplementation(Test.espressoWeb)

    debugImplementation(Test.core)
    androidTestImplementation(Test.hiltTest)
    androidTestImplementation(Test.coroutineTest)
    kaptAndroidTest(Test.daggerHilt)

    /* Dependency*/
    implementation(project(":domain"))
    implementation(project(":data"))

}

ktlint {
    android.set(true)
    outputColorName.set("RED")
}
