package com.github.tatsuyafujisaki.androidplayground.domain

import com.github.tatsuyafujisaki.androidplayground.data.MyRealmPerson
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyRealmRepositoryImpl @Inject constructor() : MyRealmRepository {
    private val realm = Realm.open(
        RealmConfiguration.Builder(schema = setOf(MyRealmPerson::class))
            .schemaVersion(schemaVersion = 1).build(),
    )

    override fun getOrNull(name: String) =
        realm.query<MyRealmPerson>("name == $0", name).find().firstOrNull()

    override suspend fun upsert(name: String, age: Int) {
        realm.write {
            copyToRealm(
                instance = MyRealmPerson().apply {
                    this.name = name
                    this.age = age
                },
                updatePolicy = UpdatePolicy.ALL,
            )
        }
    }

    override suspend fun delete(name: String) {
        realm.write {
            delete(realm.query<MyRealmPerson>("name == $0", name).find())
        }
    }
}
