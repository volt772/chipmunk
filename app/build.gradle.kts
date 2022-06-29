plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    compileSdk = Dependency.Version.compileSdkVersion

    defaultConfig {
        applicationId = "com.apx6.chipmunk"
        minSdk = Dependency.Version.minSdkVersion
        targetSdk = Dependency.Version.targetSdkVersion
        versionCode = Dependency.Version.versionCode
        versionName = Dependency.Version.versionName

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
        getByName("release") {
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
    exclude(group = Dependency.Configs.group, module = Dependency.Configs.module)
}

dependencies {

    /* Room*/
    implementation(Dependency.Room.core)
    implementation(Dependency.Room.rxJava2)
    implementation(Dependency.Room.ktx)
    kapt(Dependency.Room.compiler)

    /* Material*/
    implementation(Dependency.Material.core)

    /* Retrofit*/
    implementation(Dependency.Retrofit.core)
    implementation(Dependency.Retrofit.converterGson)
    implementation(Dependency.Retrofit.adapterRxJava)
    implementation(Dependency.Retrofit.adapterRxJava2)
    implementation(Dependency.Retrofit.loggingInterceptor)

    /* PreferenceManager*/
    implementation(Dependency.PrefManager.ktx)

    /* Glide*/
    implementation(Dependency.Glide.core)

    /* Joda time*/
    implementation(Dependency.Joda.core)

    /* Hilt*/
    implementation(Dependency.Hilt.dagger)
    kapt(Dependency.Hilt.compiler)

    /* Badge Count*/
    implementation(Dependency.Badge.shortcutBadge)

    /* Firebase*/
    implementation(platform(Dependency.Firebase.bom))
    implementation(Dependency.Firebase.analytics)
    implementation(Dependency.Firebase.core)
    implementation(Dependency.Firebase.messaging)
    implementation(Dependency.Firebase.config)
    implementation(Dependency.Firebase.configKtx)
    implementation(Dependency.Firebase.analyticsKtx)
    implementation(Dependency.Firebase.crashlytics)
    implementation(Dependency.Firebase.volley)

    implementation(
        group = Dependency.Kakao.Sdk.group,
        name = Dependency.Kakao.Sdk.name,
        version = Dependency.Kakao.Sdk.version
    )
    implementation(Dependency.Kakao.user)

    androidTestImplementation(Dependency.Test.jUnit)
    androidTestImplementation(Dependency.Test.workTest)
    androidTestImplementation(Dependency.Test.coreTest)
    androidTestImplementation(Dependency.Test.coreKtx)
    androidTestImplementation(Dependency.Test.espresso)
    androidTestImplementation(Dependency.Test.espressoContrib)
    androidTestImplementation(Dependency.Test.espressoIntents)
    androidTestImplementation(Dependency.Test.jUnitExt)
    androidTestImplementation(Dependency.Test.jUnitKtx)
    androidTestImplementation(Dependency.Test.rules)
    androidTestImplementation(Dependency.Test.runner)
    androidTestImplementation(Dependency.Test.fragment)
    androidTestImplementation(Dependency.Test.espressoWeb)

    debugImplementation(Dependency.Test.core)
    androidTestImplementation(Dependency.Test.hiltTest)
    androidTestImplementation(Dependency.Test.coroutineTest)
    kaptAndroidTest(Dependency.Test.daggerHilt)
}