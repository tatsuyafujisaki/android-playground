package com.github.tatsuyafujisaki.androidplayground.network

import com.github.tatsuyafujisaki.androidplayground.dataclass.BookList
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleApiService {
    @GET("books/v1/volumes")
    suspend fun getBooks(@Query("q") q: String): BookList
}
