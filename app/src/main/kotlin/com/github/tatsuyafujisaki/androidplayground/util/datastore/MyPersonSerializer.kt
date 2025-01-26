package com.github.tatsuyafujisaki.androidplayground.util.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.github.tatsuyafujisaki.androidplayground.MyPerson
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object MyPersonSerializer : Serializer<MyPerson> {
    override val defaultValue: MyPerson = MyPerson.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): MyPerson {
        try {
            return MyPerson.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: MyPerson,
        output: OutputStream,
    ) = t.writeTo(output)
}
