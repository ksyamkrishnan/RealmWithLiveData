package com.sca.realmwithlivedata

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.sca.realmwithlivedata.models.Student
import com.sca.realmwithlivedata.repository.RealmStudentRepository
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var studentsResult: RealmResults<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        populateListView()
    }

    private fun populateListView() {
        var repo = RealmStudentRepository()
        repo.getStudents().subscribe({
            viewAdapter.updateDataToList(it)
        }, {
            // TODO ERROR
        })
    }

    fun initRecyclerView() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(emptyList<Student>().toMutableList())
        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            // setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }
    }

    fun enterData(view: View) {
        var student = Student()
        student.name = name.text.toString()
        student.age = age.text.toString()

        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.copyToRealm(student)
        realm.commitTransaction()

    }
}
