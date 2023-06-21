package com.github.tatsuyafujisaki.androidplayground.network

import com.github.tatsuyafujisaki.androidplayground.data.BookList
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleApiService {
    @GET("books/v1/volumes")
    suspend fun getBooks(@Query("q") q: String): BookList
}
