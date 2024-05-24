# Difference between Uri.query and Uri.encodedQuery
- `Uri.query` always returns a decoded query string, regardless of whether the original query string is encoded or not.
- `Uri.encodedQuery` returns a query string as-is.
    - `Uri.encodedQuery` returns an encoded query string if the original query string is encoded.
    - `Uri.encodedQuery` returns an unencoded query string if the original query string is not encoded.

## Example
```kotlin
val uri = "https://www.google.com/search?q=hello world".toUri()
val query = uri.query.orEmpty() // q=hello world
val encodedQuery = uri.encodedQuery.orEmpty() // q=hello world (NOT hello%20world)
```

```kotlin
val uri = "https://www.google.com/search?q=hello%20world".toUri()
val query = uri.query.orEmpty() // q=hello world
val encodedQuery = uri.encodedQuery.orEmpty() // q=hello%20world
```
