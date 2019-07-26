package com.sca.realmwithlivedata.repository

import com.sca.realmwithlivedata.models.Student
import io.reactivex.Flowable
import io.realm.Realm
import io.realm.RealmResults



class RealmStudentRepository {

    fun getStudents(): Flowable<List<Student>> {
        Realm.getDefaultInstance().use({ realm ->
            val query = realm.where(Student::class.java)
            val flowable: Flowable<RealmResults<Student>>
            if (realm.isAutoRefresh()) { // for looper threads. Use `switchMap()`!
                flowable = query
                    .findAll()
                    .asFlowable()
            } else { // for background threads
                flowable = Flowable.just(query.findAll())
            }

            return flowable as Flowable<List<Student>>
        })
    }
}