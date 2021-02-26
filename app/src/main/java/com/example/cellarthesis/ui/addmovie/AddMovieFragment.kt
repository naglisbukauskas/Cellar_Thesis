package com.example.cellarthesis.ui.addmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cellarthesis.*
import com.example.cellarthesis.R
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.internal.wait
import java.io.IOException


class AddMovieFragment: Fragment() {

    private val model: MainActivityViewModel by activityViewModels()

    private lateinit var searchResultsRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_movie, container, false)

        searchResultsRecyclerView = view.findViewById(R.id.fragment_add_movie_search_results)
        searchResultsRecyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        val editText: EditText = view.findViewById(R.id.fragmentMovieEditText)

        var results: ArrayList<MovieItem> = arrayListOf()

        val gson = GsonBuilder().create()

        editText.doOnTextChanged { _: CharSequence?, _: Int, _: Int, _: Int ->
            println("Hello")

            lifecycleScope.launch(Dispatchers.Default) {
                val client = OkHttpClient()
                
                val request = Request.Builder().url("https://unogsng.p.rapidapi.com/search?query=${editText.text.toString()}").get().addHeader("x-rapidapi-key", "f174346b7amsh8eea6a1f1dd6b88p1a04c4jsna38d165ebdcd").addHeader("x-rapidapi-host", "unogsng.p.rapidapi.com").build()
                
                val response = client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        println("failure")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val body = response.body?.string()
                        
                        val testingShit: APIResultsItem = gson.fromJson(body, APIResultsItem::class.java)

                        results = arrayListOf()

                        if(testingShit.results != null) {
                            for (movie in testingShit.results) {
                                results.add(movie)
                            }
                        }

                        //results.add(testingShit.results[0])

                        //I should add a length check here

                    }
                })
            }

            searchResultsRecyclerView.adapter = SearchResultsItemAdapter(results, this)

        }

        return view

    }

    fun fetchingData(url: String) {
        model.fetchData(url)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrListObserver = Observer<ArrayList<MovieItem>> { newArrayList ->
            println("Add Movie Fragment Observer: $newArrayList")
        }

        model.movieArrayList.observe(viewLifecycleOwner, arrListObserver)
    }

}