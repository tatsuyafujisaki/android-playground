# Query
## What do a null parameter and an empty string parameter generate, respectively?
```kotlin
@GET("/api/v1/mydata")
suspend fun getMyData(
    @Query("key1") key1: String? = null,
    @Query("key2") key2: String? = "",
): Response<MyData>
```

Generated query
```
https://example.com/api/v1/mydata?key2=
```

## What query will an empty collection generate?
```kotlin
@GET("/api/v1/mydata")
suspend fun getMyData(
    @Query("key1") key1: Set<String> = emptySet(),
    @Query("key2") key2: Set<String> = setOf("a", "b"),
): Response<MyData>
```

Generated query
```
https://example.com/api/v1/mydata?key2=a&key2=b
```

# What happens if a key is missing or the value of a key is null in the JSON response?
## If the expected type of a key's value in the JSON response is array ...
Corresponding property in data class annotated with `@Serializable`|"foo" key is missing in JSON response|"foo":null in JSON response
--|--|--
val foo: List&lt;String>|[MissingFieldException](https://kotlinlang.org/api/kotlinx.serialization/kotlinx-serialization-core/kotlinx.serialization/-missing-field-exception/)|[JsonDecodingException](https://github.com/Kotlin/kotlinx.serialization/blob/master/formats/json/commonMain/src/kotlinx/serialization/json/internal/JsonExceptions.kt#L18-L21)
val foo: List&lt;String>?|[MissingFieldException](https://kotlinlang.org/api/kotlinx.serialization/kotlinx-serialization-core/kotlinx.serialization/-missing-field-exception/)|Success
val foo: List&lt;String> = emptyList()|Success|[JsonDecodingException](https://github.com/Kotlin/kotlinx.serialization/blob/master/formats/json/commonMain/src/kotlinx/serialization/json/internal/JsonExceptions.kt#L18-L21)
val foo: List&lt;String>? = null|Success|Success

## If the expected type of a key's value in the JSON response is string ...
Corresponding property in data class annotated with `@Serializable`|"foo" key is missing in JSON response|"foo":null in JSON response
--|--|--
val foo: String|[MissingFieldException](https://kotlinlang.org/api/kotlinx.serialization/kotlinx-serialization-core/kotlinx.serialization/-missing-field-exception/)|[JsonDecodingException](https://github.com/Kotlin/kotlinx.serialization/blob/master/formats/json/commonMain/src/kotlinx/serialization/json/internal/JsonExceptions.kt#L18-L21)
val foo: String?|[MissingFieldException](https://kotlinlang.org/api/kotlinx.serialization/kotlinx-serialization-core/kotlinx.serialization/-missing-field-exception/)|Success
val foo: String = ""|Success|[JsonDecodingException](https://github.com/Kotlin/kotlinx.serialization/blob/master/formats/json/commonMain/src/kotlinx/serialization/json/internal/JsonExceptions.kt#L18-L21)
val foo: String? = null|Success|Success
