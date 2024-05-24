# Difference between Uri.query and Uri.encodedQuery
- `Uri.query` always returns a decoded query string, regardless of whether the original query string is encoded or not.
- `Uri.encodedQuery` returns a query string as-is.
    - `Uri.encodedQuery` returns an encoded query string if the original query string is encoded.
    - `Uri.encodedQuery` returns an unencoded query string if the original query string is not encoded.

# Difference between Uri.path and Uri.encodedPath
- It is the same relationship between `Uri.query` and `Uri.encodedQuery`.

## Example
```kotlin
val uri = "https://example.com/a b/search?q=c d".toUri()
val path = uri.path.orEmpty() // /a b/search
val encodedPath = uri.encodedPath.orEmpty() // /a b/search
val query = uri.query.orEmpty() // q=c d
val encodedQuery = uri.encodedQuery.orEmpty() // q=c d
```

```kotlin
val uri = "https://example.com/a%20b/search?q=c%20d".toUri()
val path = uri.path.orEmpty() // /a b/search
val encodedPath = uri.encodedPath.orEmpty() // /a%20b/search
val query = uri.query.orEmpty() // q=c d
val encodedQuery = uri.encodedQuery.orEmpty() // q=c%20d
```
