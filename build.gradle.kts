// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {


    repositories {
        google()
        mavenCentral()
        maven("https://devrepo.kakao.com/nexus/content/groups/public/")
    }
    dependencies {
        classpath (Dependencies.gradle)
        classpath (Dependencies.kotlinPlugin)
        classpath (Dependencies.hiltPlugin)
        classpath (Dependencies.gmsGoogleService)
        classpath (Dependencies.firebaseCrashlytics)
        classpath (Dependencies.ktLint)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins {
    id("com.android.application") version "7.2.1" apply false
    id("com.android.library") version "7.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.6.10" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
