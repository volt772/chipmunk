plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.firebase.crashlytics")
}

android {
    compileSdk = Version.compileSdkVersion

    defaultConfig {
        minSdk = Version.minSdkVersion
        targetSdk = Version.targetSdkVersion

        testInstrumentationRunner = "com.apx6.domain.chipmunk.CustomTestRunner"
    }
}

dependencies {

    /* Room*/
    implementation(Room.core)
    implementation(Room.rxJava2)
    implementation(Room.ktx)
    kapt(Room.compiler)

    /* Hilt*/
    implementation(Hilt.dagger)
    kapt(Hilt.compiler)

    /* Retrofit*/
    implementation(Retrofit.core)
    implementation(Retrofit.converterGson)
    implementation(Retrofit.adapterRxJava)
    implementation(Retrofit.adapterRxJava2)
    implementation(Retrofit.loggingInterceptor)

    /* Paging3*/
    implementation(Paging3.ktx)
    implementation(Paging3.rxJava3)
    implementation(Paging3.roomPaging)

    /* Firebase*/
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

    implementation(Android.coreKtx)
    androidTestImplementation(Test.hiltTest)
    androidTestImplementation(Test.coroutineTest)
    kaptAndroidTest(Test.daggerHilt)

    androidTestImplementation(Test.jUnit)
    androidTestImplementation(Test.jUnitExt)
    androidTestImplementation(Test.jUnitKtx)
    androidTestImplementation(Test.rules)
    androidTestImplementation(Test.runner)
}