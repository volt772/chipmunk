
object Version {
    const val compileSdkVersion = 32
    const val minSdkVersion = 30
    const val targetSdkVersion = 32
    const val versionCode = 60001
    const val versionName = "6.0.1"
}

object Dependencies {
    const val gradle = "com.android.tools.build:gradle:7.0.4"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21"
    const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:2.38.1"
    const val gmsGoogleService = "com.google.gms:google-services:4.3.10"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-gradle:2.9.0"
    const val ktLint = "org.jlleitschuh.gradle:ktlint-gradle:9.2.1"
}

object Android {
    const val appcompat = "androidx.appcompat:appcompat:1.2.0"
    const val activityKtx = "androidx.activity:activity-ktx:1.1.0"
    const val coreKtx = "androidx.core:core-ktx:1.8.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
}

object Configs {
    const val group = "org.jetbrains"
    const val module = "annotations-java5"
}

object Room {
    const val core = "androidx.room:room-runtime:2.5.0-alpha02"
    const val rxJava2 = "androidx.room:room-rxjava2:2.4.2"
    const val ktx = "androidx.room:room-ktx:2.5.0-alpha02"
    const val compiler = "androidx.room:room-compiler:2.5.0-alpha02"
}

object Material {
    const val core = "com.google.android.material:material:1.6.1"
}

object Retrofit {
    const val core = "com.squareup.retrofit2:retrofit:2.9.0"
    const val converterGson = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val adapterRxJava = "com.squareup.retrofit2:adapter-rxjava:2.9.0"
    const val adapterRxJava2 = "com.squareup.retrofit2:adapter-rxjava2:2.9.0"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.0"
}

object PrefManager {
    const val ktx = "androidx.preference:preference-ktx:1.2.0"
}

object Glide {
    const val core = "com.github.bumptech.glide:glide:4.12.0"
}

object Joda {
    const val core = "net.danlew:android.joda:2.10.9.1"
}

object Hilt {
    const val dagger = "com.google.dagger:hilt-android:2.38.1"
    const val compiler = "com.google.dagger:hilt-android-compiler:2.38.1"
}

object Badge {
    const val shortcutBadge = "me.leolin:ShortcutBadger:1.1.22@aar"
}

object Firebase {
    const val bom = "com.google.firebase:firebase-bom:29.1.0"
    const val analytics = "com.google.firebase:firebase-analytics-ktx"
    const val core = "com.google.firebase:firebase-core:19.0.2"
    const val messaging =  "com.google.firebase:firebase-messaging:22.0.0"
    const val config = "com.google.firebase:firebase-config:21.0.1"
    const val configKtx = "com.google.firebase:firebase-config-ktx:21.0.1"
    const val analyticsKtx = "com.google.firebase:firebase-analytics-ktx:21.0.0"
    const val crashlytics = "com.google.firebase:firebase-crashlytics:18.2.9"
    const val volley = "com.android.volley:volley:1.2.1"
}

object Kakao {
    object Sdk {
        const val group = "com.kakao.sdk"
        const val name = "usermgmt"
        const val version = "1.30.0"
    }

    const val user = "com.kakao.sdk:v2-user:2.10.0"
}

object Test {
    const val jUnit = "junit:junit:4.13.2"
    const val workTest = "android.arch.work:work-testing:1.0.1"
    const val coreTest = "androidx.arch.core:core-testing:2.1.0"
    const val coreKtx = "androidx.test:core-ktx:1.4.0"
    const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:3.4.0"
    const val espressoIntents = "androidx.test.espresso:espresso-intents:3.4.0"
    const val jUnitExt = "androidx.test.ext:junit:1.1.3"
    const val jUnitKtx = "androidx.test.ext:junit-ktx:1.1.3"
    const val rules = "androidx.test:rules:1.4.0"
    const val runner = "androidx.test:runner:1.3.0"
    const val fragment = "androidx.fragment:fragment-testing:1.3.6"
    const val espressoWeb = "androidx.test.espresso:espresso-web:3.4.0"

    const val core = "androidx.test:core:1.4.0"
    const val hiltTest = "com.google.dagger:hilt-android-testing:2.38.1"
    const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.0"
    const val daggerHilt = "com.google.dagger:hilt-android-compiler:2.38.1"
}