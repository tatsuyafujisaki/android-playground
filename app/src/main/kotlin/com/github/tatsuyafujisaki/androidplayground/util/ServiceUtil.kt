package com.github.tatsuyafujisaki.androidplayground.util

import android.app.ActivityManager
import android.content.Context

object ServiceUtil {
    object ActivityService {
        fun getMemoryInfo(context: Context): ActivityManager.MemoryInfo {
            return ActivityManager.MemoryInfo().also {
                (context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
                    .getMemoryInfo(it)
            }
        }
    }
}
