package com.github.tatsuyafujisaki.androidplayground.domain

interface MyRealmRepository {
    fun getOrNull(name: String)
    suspend fun upsert(name: String, age: Int)
    suspend fun delete(name: String)
}
