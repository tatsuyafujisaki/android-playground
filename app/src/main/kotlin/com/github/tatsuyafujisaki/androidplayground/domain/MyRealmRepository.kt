package com.github.tatsuyafujisaki.androidplayground.domain

import com.github.tatsuyafujisaki.androidplayground.data.MyRealmPerson

interface MyRealmRepository {
    fun getOrNull(name: String): MyRealmPerson?
    suspend fun upsert(name: String, age: Int)
    suspend fun delete(name: String)
}
