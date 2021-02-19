package com.example.cellarthesis.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cellarthesis.R
import com.example.cellarthesis.ExampleAdapter
import com.example.cellarthesis.ExampleItem

class LibraryFragment : Fragment() {

    private lateinit var libraryViewModel: LibraryViewModel

    private lateinit var recyclerView1: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var recyclerView3: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        libraryViewModel = ViewModelProvider(this).get(LibraryViewModel::class.java)
        //This inflates the view for the fragment
        val view = inflater.inflate(R.layout.fragment_library, container, false)

        val exampleList = generateDummyList(500)

        //RECYCLER_VIEW_1

        recyclerView1 = view.findViewById(R.id.recycler_view_1)
        recyclerView1.adapter = ExampleAdapter(exampleList)
        recyclerView1.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        //This is the main line of code that makes the recyclerView horizontal. In the main activity XML
        //the recyclerview orientation is also set as Horizontal, but without the "LinearLayoutManager.HORIZONTAL" then
        //the recyclerView still remains vertical
        recyclerView1.setHasFixedSize(true)

        //RECYCLER_VIEW_2

        recyclerView2 = view.findViewById(R.id.recycler_view_2)
        recyclerView2.adapter = ExampleAdapter(exampleList)
        recyclerView2.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView2.setHasFixedSize(true)

        //RECYCLER_VIEW_3

        recyclerView3 = view.findViewById(R.id.recycler_view_3)
        recyclerView3.adapter = ExampleAdapter(exampleList)
        recyclerView3.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView3.setHasFixedSize(true)

        //RECYCLER_VIEW_4

        recyclerView3 = view.findViewById(R.id.recycler_view_4)
        recyclerView3.adapter = ExampleAdapter(exampleList)
        recyclerView3.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView3.setHasFixedSize(true)

        return view
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