# Compatibility
## Compatibility table between Android API version and Java version
> Use this table to determine which Java version is supported by each Android API, and where to find details on which Java APIs are available.

https://developer.android.com/build/jdks#compileSdk

## Compatibility table between Android Gradle plugin (AGP) version and Gradle version
> The following table lists which version of Gradle is required for each version of the Android Gradle plugin. For the best performance, you should use the latest possible version of both Gradle and the plugin.

https://developer.android.com/build/releases/gradle-plugin#updating-gradle

## Compatibility table between Android Studio version and Android Gradle plugin (AGP) version
> The following table lists which version of AGP is required for each version of Android Studio.

https://developer.android.com/build/releases/gradle-plugin#android_gradle_plugin_and_android_studio_compatibility

## Compatibility table among Android API version, Android Studio version, and Android Gradle plugin (AGP) version
> The minimum versions of Android Studio and AGP are as follows:

https://developer.android.com/build/releases/gradle-plugin#api-level-support

## Compatibility between Java version and Gradle version
> See the table below for the Java version supported by a specific Gradle release:

https://docs.gradle.org/current/userguide/compatibility.html#java_runtime

## Compatibility between Kotlin version and Gradle version
https://docs.gradle.org/current/userguide/compatibility.html#kotlin

# Miscellaneous
## Which Java APIs can I use in my Java or Kotlin source code?
https://developer.android.com/build/jdks#compileSdk

## Which JDK compiles my Java source code?

https://developer.android.com/build/jdks#toolchain

## Which Java language source features can I use in my Java source code?

https://developer.android.com/build/jdks#source-compat

## Which Java binary features can be used when I compile my Kotlin or Java source?

https://developer.android.com/build/jdks#target-compat

# How do I fix the build error below?

> Inconsistent JVM-target compatibility detected for tasks 'compileDebugJavaWithJavac' (xxx) and 'compileDebugKotlin' (yyy).

1. Open `app/build.gradle` in Android Studio.
1. Delete the following.
    ```kotlin
    android {
      compileOptions {
          sourceCompatibility = JavaVersion.VERSION_##
          targetCompatibility = JavaVersion.VERSION_##
      }
      kotlinOptions.jvmTarget = ##
      kotlin.jvmToolchain(jdkVersion = ##)
    }
    ```
1. Write the following.
   ```kotlin
   android {
       java.toolchain.languageVersion = JavaLanguageVersion.of(##)
   }
   ```

# Best practices
## Use the JBR (JetBrains Runtime)
> We recommend that you use the JBR to run Android Studio.

https://developer.android.com/build/jdks#jdk-android-studio


## Specify the Java toolchain
> We recommend that you always specify the Java toolchain, and either ensure that the specified JDK is installed, or add a toolchain resolver to your build.

https://developer.android.com/build/jdks#toolchain

> Therefore, we recommend that you always explicitly specify these values or use a Java toolchain.

https://developer.android.com/build/jdks#target-compat
