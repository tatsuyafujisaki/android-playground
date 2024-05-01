package com.github.tatsuyafujisaki.androidplayground.domain

import com.github.tatsuyafujisaki.androidplayground.data.MyRealmPerson
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query

class MyRealmRepository {
    private val realm = Realm.open(
        RealmConfiguration
            .Builder(schema = setOf(MyRealmPerson::class))
            .schemaVersion(schemaVersion = 1)
            .build(),
    )

    fun getOrNull(name: String) =
        realm.query<MyRealmPerson>("name == $0", name).find().firstOrNull()

    fun upsert(name: String, age: Int) {
        realm.writeBlocking {
            copyToRealm(
                instance = MyRealmPerson().apply {
                    this.name = name
                    this.age = age
                },
                updatePolicy = UpdatePolicy.ALL,
            )
        }
    }
}
