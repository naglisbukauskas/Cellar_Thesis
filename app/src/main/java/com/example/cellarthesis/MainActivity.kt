package com.example.cellarthesis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var recycler_view: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val exampleList = generateDummyList(500)

        recycler_view = findViewById(R.id.recycler_view)
        recycler_view.adapter = ExampleAdapter(exampleList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)

    }

    private fun generateDummyList(size: Int): List<ExampleItem> {

        val list = ArrayList<ExampleItem>()

        for(i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_baseline_alarm_on_24
                1 -> R.drawable.ic_baseline_archive_24
                else -> R.drawable.ic_baseline_airline_seat_legroom_extra_24
            }

            val item = ExampleItem(drawable, "Item $i", "Line 2")
            list.add(item)

        }

        return list

    }

}