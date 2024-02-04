package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("")

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
