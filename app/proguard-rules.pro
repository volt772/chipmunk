-keep class com.kakao.sdk.**.model.* { <fields>; }
-keep class * extends com.google.gson.TypeAdapter

-keep public class com.google.android.gms.ads.** {
    public *;
}

# -----------------------------
# 디버그 정보 및 Annotation 보존
# -----------------------------
-keepattributes SourceFile, LineNumberTable, *Annotation*, Signature, EnclosingMethod, InnerClasses, KotlinMetadata
-renamesourcefileattribute SourceFile

# -----------------------------
# Retrofit + OkHttp + Coroutines Generic 보존 (isn't parameterized 방지)
# -----------------------------
-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *; }
-keep interface retrofit2.Call
-keep class retrofit2.Response
-keepclassmembers class retrofit2.** {
    *;
}

-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-keepclassmembers class okhttp3.** {
    *;
}

-keep class kotlin.coroutines.Continuation

# kotlinx.coroutines.flow.Flow 보존
-keep class kotlinx.coroutines.flow.Flow

# -----------------------------
# Room 관련 보존
# -----------------------------
-keep class androidx.room.** { *; }
-keepclassmembers class * {
    @androidx.room.Entity <fields>;
    @androidx.room.PrimaryKey <fields>;
    @androidx.room.Dao <methods>;
    @androidx.room.ColumnInfo <fields>;
}

# -----------------------------
# 프로젝트 패키지 보존
# -----------------------------
# -----------------------------
# GraalVM 관련 경고 무시 (OkHttp 5.x)
# -----------------------------
-dontwarn com.oracle.svm.core.annotate.**
-dontwarn org.graalvm.nativeimage.**
-dontwarn okhttp3.internal.graal.**

# -----------------------------
# WebView JS Interface 필요 시 활성화
# -----------------------------
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
