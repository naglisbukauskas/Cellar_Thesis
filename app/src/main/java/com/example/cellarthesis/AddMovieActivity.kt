package com.example.cellarthesis

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AddMovieActivity: AppCompatActivity() {

    lateinit var mainActivityViewModel: MainActivityViewModel

    //TESTING
    private lateinit var recyclerView1: RecyclerView

    private val model: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        val exampleItem = generateDummyList(500)

        val editText: EditText = findViewById(R.id.movieEditText)

        val exampleList = generateDummyList(500)

        val button: Button = findViewById(R.id.movieAddButton)

        button.setOnClickListener {
            mainActivityViewModel.fetchData()
            println("Add Movie Activity Button Clicked")
        }


        val nameObserver = Observer<ArrayList<TestItem>> {  newArrayList ->
            recyclerView1.adapter = TestItemAdapter(newArrayList as List<TestItem>)

        }

        //TESTING
        recyclerView1 = findViewById(R.id.test_recycler_view_1)
        recyclerView1.adapter = TestItemAdapter(mainActivityViewModel.getMutableTestItemArrayList() as List<TestItem>)
        recyclerView1.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        model.mutableTestItemArrayList.observe(this, nameObserver)

    }

    //TESTING

    private fun generateDummyList(size: Int): List<TestItem> {

        val list = ArrayList<TestItem>()

        for(i in 0 until size) {

            val item = TestItem("https://occ-0-2219-2218.1.nflxso.net/dnm/api/v6/evlCitJPPCVCry0BZlEFb5-QjKc/AAAABW-3KdzddLNpQk8HzQhgzN21CRvEHgPzBOQRDDF17My05gvDYi6DctxoFeSW1YsX53Wwx2Ygn0zU-4mVyadygewg3w.jpg?r=efc", "Item $i", "Line 2")
            list.add(item)

        }

        return list

    }
    
}