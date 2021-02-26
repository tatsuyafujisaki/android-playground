package com.github.tatsuyafujisaki.androidplayground.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.preference.PreferenceManager

/**
 * Impractical redundant explanatory wrappers
 */
object SharedPreferenceUtil {
    val Context.sharedPreferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(this)

    fun Context.sharedPreferences(name: String): SharedPreferences =
        getSharedPreferences(name, Context.MODE_PRIVATE)

    fun example(sharedPreferences: SharedPreferences, key: String) {
        val exists: Boolean = sharedPreferences.contains(key)
        val map: Map<String, *> = sharedPreferences.all
    }

    fun SharedPreferences.remove(key: String) {
        edit {
            remove(key)
        }
    }

    fun SharedPreferences.clear() {
        edit {
            clear()
        }
    }

    fun SharedPreferences.getBoolean(key: String) = getBoolean(key, false)
    fun SharedPreferences.getFloat(key: String) = getFloat(key, Float.MIN_VALUE)
    fun SharedPreferences.getInt(key: String) = getInt(key, Int.MIN_VALUE)
    fun SharedPreferences.getLong(key: String) = getLong(key, Long.MIN_VALUE)
    fun SharedPreferences.getString(key: String) = getString(key, null)
    fun SharedPreferences.getStringSet(key: String): MutableSet<String>? = getStringSet(key, null)

    fun SharedPreferences.putBoolean(key: String, value: Boolean) {
        edit {
            putBoolean(key, value)
        }
    }

    fun SharedPreferences.putFloat(key: String, value: Float) {
        edit {
            putFloat(key, value)
        }
    }

    fun SharedPreferences.putInt(key: String, value: Int) {
        edit {
            putInt(key, value)
        }
    }

    fun SharedPreferences.putLong(key: String, value: Long) {
        edit {
            putLong(key, value)
        }
    }

    fun SharedPreferences.putString(key: String, value: String) {
        edit {
            putString(key, value)
        }
    }

    fun SharedPreferences.putStringSet(key: String, value: Set<String>) {
        edit {
            putStringSet(key, value)
        }
    }

    class SharedPreferencesObserver(
        private val sharedPreferences: SharedPreferences
    ) : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun registerSomething() {
            sharedPreferences
                .registerOnSharedPreferenceChangeListener { _, key ->
                    Log.d(this::class.java.simpleName, key)
                }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun unregisterSomething() {
            sharedPreferences
                .unregisterOnSharedPreferenceChangeListener { _, key ->
                    Log.d(this::class.java.simpleName, key)
                }
        }
    }
}
