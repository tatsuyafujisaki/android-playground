# Which Java APIs can I use in my Java or Kotlin source code?

https://developer.android.com/build/jdks#compileSdk

# Which JDK compiles my Java source code?

https://developer.android.com/build/jdks#toolchain

# Which Java language source features can I use in my Java source code?

https://developer.android.com/build/jdks#source-compat

# Which Java binary features can be used when I compile my Kotlin or Java source?

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

> We recommend that you use the JBR to run Android Studio.

https://developer.android.com/build/jdks#jdk-android-studio

> We recommend that you always specify the Java toolchain, and either ensure that the specified JDK is installed, or add a toolchain resolver to your build.

https://developer.android.com/build/jdks#toolchain

> Therefore, we recommend that you always explicitly specify these values or use a Java toolchain.

https://developer.android.com/build/jdks#target-compat
