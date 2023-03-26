package com.github.tatsuyafujisaki.androidplayground.util

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

/**
 * Impractical redundant explanatory wrappers
 */
object SharedPreferencesUtil {
    /**
     * How to get the default [SharedPreferences]:
     * [PreferenceManager.getDefaultSharedPreferences]
     */

    /**
     * How to get a named [SharedPreferences]:
     * [Context.getSharedPreferences]
     */

    /**
     * How to get a [SharedPreferences], which is private to an activity.
     * [Activity.getPreferences]
     */

    /**
     * How to get all the entries in [SharedPreferencesUtil]:
     * [SharedPreferences.getAll]
     */

    /**
     * How to check is a key exists in [SharedPreferencesUtil]:
     * [SharedPreferences.contains]
     */

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
    fun SharedPreferences.getString(key: String) = getString(key, "")
    fun SharedPreferences.getStringSet(key: String): MutableSet<String>? =
        getStringSet(key, emptySet())

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
}
