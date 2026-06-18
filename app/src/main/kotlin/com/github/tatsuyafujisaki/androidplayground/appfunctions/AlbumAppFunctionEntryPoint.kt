package com.github.tatsuyafujisaki.androidplayground.appfunctions

import androidx.appfunctions.AppFunctionContext
import androidx.appfunctions.AppFunctionService
import androidx.appfunctions.service.AppFunction
import androidx.appfunctions.service.AppFunctionEntryPoint
import com.github.tatsuyafujisaki.androidplayground.data.Album
import com.github.tatsuyafujisaki.androidplayground.data.AlbumToPost
import com.github.tatsuyafujisaki.androidplayground.hilt.AppFunctionEntryPoint as HiltAppFunctionEntryPoint
import com.github.tatsuyafujisaki.androidplayground.network.JsonPlaceholderService
import dagger.hilt.EntryPoints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@AppFunctionEntryPoint(
    serviceName = "AlbumAppFunctionService",
    appFunctionXmlFileName = "album_app_functions"
)
abstract class AlbumAppFunctionEntryPoint : AppFunctionService() {

    private lateinit var service: JsonPlaceholderService

    override fun onCreate() {
        super.onCreate()
        service = EntryPoints.get(
            applicationContext,
            HiltAppFunctionEntryPoint::class.java
        ).jsonPlaceholderService()
    }

    /**
     * Create a new album with the given title.
     *
     * @param appFunctionContext The execution context.
     * @param title The title of the album.
     * @return The created album including its generated ID.
     */
    @AppFunction(isDescribedByKDoc = true)
    suspend fun createAlbum(
        appFunctionContext: AppFunctionContext,
        title: String
    ): Album = withContext(Dispatchers.IO) {
        service.postAlbum(AlbumToPost(title))
    }

    /**
     * Retrieve an album by its unique identifier.
     *
     * @param appFunctionContext The execution context.
     * @param id The unique identifier of the album to retrieve.
     * @return The album with the specified ID.
     */
    @AppFunction(isDescribedByKDoc = true)
    suspend fun getAlbum(
        appFunctionContext: AppFunctionContext,
        id: Int
    ): Album = withContext(Dispatchers.IO) {
        service.getAlbum(id)
    }
}
