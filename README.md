# Best practices
* Use `bundleOf()` in Core KTX to create a Bundle.
* Use `CharSequence.isDigitsOnly()` in Core KTX to check if a string contains only digits.
  * FYI, `CharSequence.isDigitsOnly()` returns true for an empty string.
* Use `String.toUri()` in Core KTX rather than `Uri.parse(...)` for simplicity.

# How to decode HTML entities
```kotlin
val decoded = Html.fromHtml("&amp;&gt;&lt;&nbsp;&quot;", Html.FROM_HTML_MODE_COMPACT).toString() // "&>< ""
```

# How to get a query parameter
```kotlin
val url = "https://example.com?key1=value1&key2=value2"

// String.toUri() is from Core KTX.
val value2 = url.toUri().getQueryParameter("key2") // value2
```

# How to run a unit test or an instrumented unit test from Terminal
## How to run a local unit test
```shell
./gradlew test
```

## How to run an instrumented unit test
```shell
./gradlew connectedAndroidTest
```

## How to run a local unit test for a build variant and a module
```shell
./gradlew <module>:test<build-variant>UnitTest
```

## How to run an instrumented unit test for a build variant and a module
```shell
./gradlew <module>:connected<build-variant>AndroidTest
```

# How to find what class causes an error by `minifyenabled true`
Add the following in `proguard-rule.pro`.
```
-keepattributes SourceFile,LineNumberTable
```

# How to remove comments
1. Command+Shift+F in Android Studio.
2. Check `Regex`.
3. Put one of the following regexes.
4. Click `Replace All`.

## Regex to match all the comments (/* \*/ including /** \*/)
```
/\*([\S\s]+?)\*/
```

## Regex to match copyright comments (/* \*/ including /** \*/)
```
/\*([\S\s]+?Copyright[\S\s]+?)\*/
```

## Regex to match copyright comments in XML
```
<!--([\S\s]+?Copyright[\S\s]+?)-->
```

# Charles
## How to enable Charles Proxy in debug build
https://www.charlesproxy.com/documentation/using-charles/ssl-certificates/

## How to enable Charles Proxy in release build
1. From Android, access http://www.charlesproxy.com/getssl/ to download Charles Root Certificate.
2. Open Charles and go to the menu bar > `Help` > `SSL Proxying` > `Save Charles Root Certificate`
3. Add the downloaded PEM file as `res/raw/charles_certificate.pem`.
4. Add `ref/xml/network_security_config.xml` as follows.
```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <base-config>
        <trust-anchors>
            <certificates src="system" />
            <certificates src="@raw/charles_certificate"/>
            <!-- The following in <base-config> has no effect. -->
            <!-- <certificates src="user" /> -->
        </trust-anchors>
    </base-config>
</network-security-config>
```
5. Reference `ref/xml/network_security_config.xml` in `AndroidManifest.xml` as follows.
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest>
    <application android:networkSecurityConfig="@xml/network_security_config" />
</manifest>
```
