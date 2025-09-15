## ----------------------------
## Core module
## ----------------------------
#-dontwarn com.example.core.**
#-keep class com.example.core.** { *; }
#
## ----------------------------
## Hilt / Dagger
## ----------------------------
#-keep class dagger.hilt.** { *; }
#-keep class androidx.hilt.** { *; }
#-keep class dagger.internal.codegen.** { *; }
#-dontwarn dagger.hilt.internal.**
#-keepattributes *Annotation*
#
## ----------------------------
## Retrofit & OkHttp
## ----------------------------
#-keep class retrofit2.** { *; }
#-keep interface com.example.core.data.source.remote.ApiService { *; }
#-keep class com.example.core.data.source.remote.response.** { *; }
#-keepattributes Signature, RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
#-keep @retrofit2.http.* class * { *; }
#-keep class okhttp3.** { *; }
#-dontwarn okhttp3.**
#-dontwarn okio.**
#
## ----------------------------
## Gson / Moshi
## ----------------------------
#-keep class com.google.gson.** { *; }
#-dontwarn com.google.gson.**
#-keepattributes Signature, RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
#
## ----------------------------
## Room
## ----------------------------
#-keep class androidx.room.** { *; }
#-keep @androidx.room.* class * { *; }
#
## ----------------------------
## Kotlin Coroutines
## ----------------------------
#-dontwarn kotlinx.coroutines.**
#
## ----------------------------
## SQLCipher
## ----------------------------
#-keep,includedescriptorclasses class net.sqlcipher.** { *; }
#-keep,includedescriptorclasses interface net.sqlcipher.** { *; }
#-keepclassmembers class net.sqlcipher.database.SQLiteDatabase {
#    public static void loadLibs(...);
#    public static void loadLibs(...);
#}


# Core module classes
-keep class com.example.core.** { *; }
-dontwarn com.example.core.**

# Retrofit service
-keep interface com.example.core.data.source.remote.ApiService { *; }

# Retrofit annotations
-keepattributes Signature, RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
-keep @retrofit2.http.* class * { *; }

# Retrofit models / responses
-keep class com.example.core.data.source.remote.response.** { *; }

# Gson
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**
