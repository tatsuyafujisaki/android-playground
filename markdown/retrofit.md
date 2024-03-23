### What query will null and an empty string generate, respestively?
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

### What query will an empty collection generate?
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