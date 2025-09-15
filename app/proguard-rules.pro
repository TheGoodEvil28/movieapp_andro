-keep class com.example.core.** { *; }
-dontwarn com.example.core.**

-keep class com.example.favorite.** { *; }
-dontwarn com.example.favorite.**

-keep class dagger.hilt.** { *; }
-keep class androidx.hilt.** { *; }
-dontwarn dagger.hilt.internal.**

-keep class retrofit2.** { *; }
-keep interface com.example.core.data.source.remote.ApiService { *; }
-keep class com.example.core.data.source.remote.response.** { *; }
-keepattributes Signature, RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
-keep @retrofit2.http.* class * { *; }

-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**

-keep class androidx.room.** { *; }
-keep @androidx.room.* class * { *; }

-keep class okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**
