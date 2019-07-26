package com.sca.realmwithlivedata.models

import io.realm.RealmObject

open class Student(
    var name: String?= null,
    var age: String?= null
) : RealmObject(){}