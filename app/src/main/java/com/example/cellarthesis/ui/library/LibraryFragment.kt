package com.example.cellarthesis.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cellarthesis.*

class LibraryFragment : Fragment() {
    //data should absolutely NOT be called from the Fragment, it needs to be called from the ViewModel.

    var initTest: Boolean = false

    /*private val model: MainActivityViewModel by viewModels()
    private lateinit var mainActivityViewModel: MainActivityViewModel*/

    private val model: MainActivityViewModel by activityViewModels()
    //private lateinit var mainActivityViewModel: MainActivityViewModel*/

    private lateinit var recyclerView1: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var recyclerView3: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_library, container, false)

        //mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)


        //This is the main line of code that makes the recyclerView horizontal. In the main activity XML
        //the recyclerview orientation is also set as Horizontal, but without the "LinearLayoutManager.HORIZONTAL" then
        //the recyclerView still remains vertical
        recyclerView1 = view.findViewById(R.id.recycler_view_1)
        //recyclerView1.adapter = MovieItemAdapter(model.movieArrayList.value as List<MovieItem>)
        recyclerView1.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView1.setHasFixedSize(true)

        recyclerView2 = view.findViewById(R.id.recycler_view_2)
        //recyclerView2.adapter = MovieItemAdapter(model.movieArrayList.value as List<MovieItem>)
        recyclerView2.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView2.setHasFixedSize(true)

        recyclerView3 = view.findViewById(R.id.recycler_view_3)
        //recyclerView3.adapter = MovieItemAdapter(model.movieArrayList.value as List<MovieItem>)
        recyclerView3.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView3.setHasFixedSize(true)

        recyclerView3 = view.findViewById(R.id.recycler_view_4)
        //recyclerView3.adapter = MovieItemAdapter(model.movieArrayList.value as List<MovieItem>)
        recyclerView3.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView3.setHasFixedSize(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrListObserver = Observer<ArrayList<MovieItem>> { newArrayList ->
            if(newArrayList.size > 0) {
                recyclerView1.adapter = MovieItemAdapter(newArrayList as List<MovieItem>)
                view.findViewById<TextView>(R.id.library_fragment_placeholder_text).visibility = View.INVISIBLE
                println("Library Fragment Observer: $newArrayList")
            }

        }

        model.movieArrayList.observe(viewLifecycleOwner, arrListObserver)
    }

}