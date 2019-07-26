package com.sca.realmwithlivedata

import android.app.Application
import io.realm.Realm

class RealmApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}