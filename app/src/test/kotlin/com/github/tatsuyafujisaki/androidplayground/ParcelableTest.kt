package com.github.tatsuyafujisaki.androidplayground

import android.os.Parcel
import android.os.Parcelable
import kotlin.test.assertEquals

object ParcelableTest {
    fun <T : Parcelable> assertParcelable(original: T) {
        assertEquals(original, saveAndRead(original))
    }

    private fun <T : Parcelable> saveAndRead(data: T): T = with(Parcel.obtain()) {
        writeParcelable(data, 0)
        setDataPosition(0)
        readParcelable(data.javaClass.classLoader)!!
    }
}
