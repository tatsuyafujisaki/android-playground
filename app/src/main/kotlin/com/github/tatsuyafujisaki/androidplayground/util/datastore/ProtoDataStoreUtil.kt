package com.github.tatsuyafujisaki.androidplayground.util.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.github.tatsuyafujisaki.androidplayground.MyPerson

val Context.myPersonProtoDataStore: DataStore<MyPerson> by dataStore(
    fileName = "my_person.proto",
    serializer = MyPersonSerializer,
)

object ProtoDataStoreUtil {
    suspend fun <T> clear(context: Context) {
        context.myPersonProtoDataStore.updateData {
            it.toBuilder().clear().build()
        }
    }
}
