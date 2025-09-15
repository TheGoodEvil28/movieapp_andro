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

# OkHttp
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**
-dontwarn okio.**
