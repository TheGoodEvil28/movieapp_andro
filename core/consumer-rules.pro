# Core module consumer rules
-keep class com.example.core.domain.model.** { *; }
-keep class com.example.core.data.source.local.entity.** { *; }
-keep interface com.example.core.data.source.remote.ApiService


# Keep Hilt generated classes
-keep class dagger.hilt.internal.** { *; }
-keep class * extends dagger.hilt.EntryPoint
-keep class * extends dagger.hilt.InstallIn
-keep class * extends dagger.hilt.android.internal.managers.** { *; }

# Keep your module's injectors & ViewModels
-keep class com.example.favorite.** { *; }


-keep,includedescriptorclasses class net.sqlcipher.** { *; }
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }

-keep class com.example.core.** { *; }
-dontwarn com.example.core.**


-keep class com.example.favorite.** { *; }
-dontwarn com.example.favorite.**
