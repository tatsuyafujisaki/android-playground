package com.github.tatsuyafujisaki.androidplayground.network

import com.github.tatsuyafujisaki.androidplayground.data.Album
import com.github.tatsuyafujisaki.androidplayground.data.AlbumToPost
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * https://jsonplaceholder.typicode.com
 */
interface JsonPlaceholderService {
    @GET("albums")
    suspend fun getAlbums(): List<Album>

    @GET("albums/{id}")
    suspend fun getAlbum(@Path("id") id: Int): Album

    @POST("albums")
    suspend fun postAlbum(@Body album: AlbumToPost): Album

    @DELETE("albums/{id}")
    suspend fun deleteAlbum(@Path("id") id: Int)
}
