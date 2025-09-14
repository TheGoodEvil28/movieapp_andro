# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# ----------------------------
# ✅ Core module (MovieApp)
# ----------------------------
# Jangan warning kalau ada class core yang hilang
-dontwarn com.example.core.**

# Keep semua class di core (supaya Repository, UseCase, Model dll. gak dihapus)
-keep class com.example.core.** { *; }

# ----------------------------
# ✅ Hilt / Dagger
# ----------------------------
-keep class dagger.hilt.** { *; }
-keep class androidx.hilt.** { *; }
-keep class dagger.internal.codegen.** { *; }
-dontwarn dagger.hilt.internal.**

# ----------------------------
# ✅ Retrofit & OkHttp
# ----------------------------
-keep class retrofit2.** { *; }
-keep interface com.example.core.data.source.remote.ApiService
-dontwarn okhttp3.**

# ----------------------------
# ✅ Room
# ----------------------------
-keep class androidx.room.** { *; }
-keep @androidx.room.* class * { *; }

# ----------------------------
# ✅ Kotlin coroutines
# ----------------------------
-dontwarn kotlinx.coroutines.**

# ----------------------------
# ✅ Model / Entity
# ----------------------------
-keep class com.example.core.domain.model.** { *; }
-keep class com.example.core.data.source.local.entity.** { *; }


# SQLCipher JNI bridge — jangan obfuscate
-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }

# Keep native method signatures (safety)
-keepclassmembers class net.sqlcipher.database.SQLiteDatabase {
    public static void loadLibs(...);
    public static void loadLibs(...);
}

# Hilt & generated stuff
-keep class dagger.hilt.** { *; }
-keep class androidx.hilt.** { *; }
-dontwarn dagger.hilt.internal.**

# Keep room/dao/entities
-keep class androidx.room.** { *; }
-keep @androidx.room.* class * { *; }

# Keep Retrofit/Gson reflection usages (if needed)
-keepattributes Signature,RuntimeVisibleAnnotations,RuntimeVisibleParameterAnnotations
-keep class retrofit2.** { *; }
-keep class com.google.gson.** { *; }
