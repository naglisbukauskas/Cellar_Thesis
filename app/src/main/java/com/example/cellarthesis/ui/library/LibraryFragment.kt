package com.example.cellarthesis.ui.library

import android.graphics.Movie
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cellarthesis.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class LibraryFragment : Fragment() {
    //data should absolutely NOT be called from the Fragment, it needs to be called from the ViewModel.


    /*private val model: MainActivityViewModel by viewModels()
    private lateinit var mainActivityViewModel: MainActivityViewModel*/

    private val model: MainActivityViewModel by activityViewModels()
    //private lateinit var mainActivityViewModel: MainActivityViewModel*/

    /*private lateinit var recyclerView1: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var recyclerView3: RecyclerView
    private lateinit var recyclerView4: RecyclerView*/

    private lateinit var recyclerView1: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var recyclerView3: RecyclerView
    private lateinit var recyclerView4: RecyclerView

    private val nArrList: ArrayList<MovieItem> = arrayListOf()
    private val aArrList: ArrayList<MovieItem> = arrayListOf()
    private val hpArrList: ArrayList<MovieItem> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_library, container, false)

        //mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)



        //FAB
        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.nav_add_movie)
        }


        //This is the main line of code that makes the recyclerView horizontal. In the main activity XML
        //the recyclerview orientation is also set as Horizontal, but without the "LinearLayoutManager.HORIZONTAL" then
        //the recyclerView still remains vertical
        recyclerView1 = view.findViewById(R.id.recycler_view_1)
        recyclerView1.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        recyclerView2 = view.findViewById(R.id.recycler_view_2)
        recyclerView2.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        recyclerView3 = view.findViewById(R.id.recycler_view_3)
        recyclerView3.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        recyclerView4 = view.findViewById(R.id.recycler_view_4)
        recyclerView4.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(model.subscriptionList.value == null) {
            recyclerView1
            recyclerView2
            recyclerView3
            recyclerView4

        }

        val arrListObserver = Observer<ArrayList<MovieItem>> { newArrayList ->
            if(newArrayList.size > 0) {
                for(movie in newArrayList) {
                    if(movie.platform == "Netflix" && !nArrList.contains(movie)) {
                        nArrList.add(movie)
                    } else if (movie.platform == "Amazon" && !aArrList.contains(movie)) {
                        aArrList.add(movie)
                    } else if (movie.platform == "Hulu Plus" && !hpArrList.contains(movie)) {
                        hpArrList.add(movie)
                    }
                }
                /*recyclerView1.adapter = MovieItemAdapter(newArrayList as List<MovieItem>)
                recyclerView4.adapter = MovieItemAdapter(newArrayList as List<MovieItem>)*/
                recyclerView1.adapter = MovieItemAdapter(nArrList as List<MovieItem>)
                recyclerView2.adapter = MovieItemAdapter(aArrList as List<MovieItem>)
                recyclerView3.adapter = MovieItemAdapter(hpArrList as List<MovieItem>)
                view.findViewById<TextView>(R.id.library_fragment_placeholder_text).visibility = View.INVISIBLE
                println("Library Fragment Observer: $newArrayList")
            }

        }

        model.movieArrayList.observe(viewLifecycleOwner, arrListObserver)
    }

}