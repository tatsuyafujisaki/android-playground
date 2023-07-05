package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.github.tatsuyafujisaki.androidplayground.data.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

object DataStoreUtil {
    suspend fun <T> get(context: Context, key: Preferences.Key<T>, defaultValue: T) =
        context.dataStore.data.map { it[key] ?: defaultValue }.first()

    suspend fun <T> set(context: Context, key: Preferences.Key<T>, value: T) = runCatching {
        context.dataStore.edit { it[key] = value }
    }.isSuccess

    suspend fun <T> remove(context: Context, key: Preferences.Key<T>) = runCatching {
        context.dataStore.edit { it.remove(key) }
    }.isSuccess
}
