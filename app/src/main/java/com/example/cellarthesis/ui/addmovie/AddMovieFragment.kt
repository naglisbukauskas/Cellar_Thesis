package com.example.cellarthesis.ui.addmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cellarthesis.*
import com.example.cellarthesis.R
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.internal.wait
import java.io.IOException


class AddMovieFragment: Fragment(), AdapterView.OnItemSelectedListener {

    private val model: MainActivityViewModel by activityViewModels()

    private lateinit var searchResultsRecyclerView: RecyclerView
    var results: ArrayList<MovieItem> = arrayListOf()
    private lateinit var editText: EditText
    private lateinit var gson: Gson
    private var spinnerSelection: String = "Netflix"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_movie, container, false)

        searchResultsRecyclerView = view.findViewById(R.id.fragment_add_movie_search_results)
        searchResultsRecyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        val spinner: Spinner = view.findViewById(R.id.svod_spinner)

        if (container != null) {
            ArrayAdapter.createFromResource(
                container.context,
                R.array.svod_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
                spinner.onItemSelectedListener = this
            }
        }






        editText= view.findViewById(R.id.fragmentMovieEditText)

        gson = GsonBuilder().create()


        editText.doOnTextChanged { _: CharSequence?, _: Int, _: Int, _: Int ->

            generateSearchResults()

        }

        return view

    }

    fun fetchingData(url: String, platform: String) {
        model.fetchData(url, platform)
    }

    private fun generateSearchResults() {

        if(spinnerSelection == "Netflix") {
            lifecycleScope.launch(Dispatchers.Default) {
                val client = OkHttpClient()
                //fetch movies from the text box
                val request = Request.Builder()
                    .url("https://unogsng.p.rapidapi.com/search?query=${editText.text.toString()}")
                    .get()
                    .addHeader(
                        "x-rapidapi-key",
                        "f174346b7amsh8eea6a1f1dd6b88p1a04c4jsna38d165ebdcd"
                    )
                    .addHeader("x-rapidapi-host", "unogsng.p.rapidapi.com")
                    .build()
                val response = client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        println("failure")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val body = response.body?.string()
                        val testingShit: APIResultsItem =
                            gson.fromJson(body, APIResultsItem::class.java)

                        results = arrayListOf()


                        if (testingShit.results != null) {
                            for (movie in testingShit.results) {
                                movie.platform = "Netflix"
                                results.add(movie)
                            }
                        }
                    }
                })
            }
            searchResultsRecyclerView.adapter = SearchResultsItemAdapter(results, this)
        } else if (spinnerSelection == "Amazon") {
            lifecycleScope.launch(Dispatchers.Default) {
                val client = OkHttpClient()
                //fetch movies from the text box
                val request = Request.Builder()
                    .url("https://streamzui-streamzui-v1.p.rapidapi.com/search?page=1&country=us&type=Movie&title=${editText.text.toString()}")
                    .get()
                    .addHeader("x-rapidapi-key", "f174346b7amsh8eea6a1f1dd6b88p1a04c4jsna38d165ebdcd")
                    .addHeader("x-rapidapi-host", "streamzui-streamzui-v1.p.rapidapi.com")
                    .build()
                val response = client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        println("failure")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val body = response.body?.string()
                        println("Amazon $body")
                        val testingShit: AmazonAPIResultsItem =
                            gson.fromJson(body, AmazonAPIResultsItem::class.java)

                        results = arrayListOf()


                        if (testingShit.Results != null) {
                            for (movie in testingShit.Results) {
                                results.add(MovieItem(movie.Title, "", 0,movie.Year,0.0, 0.0,"", "Amazon",movie.imdbID))
                            }
                        }

                        //results.add(testingShit.results[0])

                        //I should add a length check here

                    }
                })
            }
            searchResultsRecyclerView.adapter = SearchResultsItemAdapter(results, this)
        } else if (spinnerSelection == "Hulu Plus") {
            lifecycleScope.launch(Dispatchers.Default) {
                val client = OkHttpClient()
                //fetch movies from the text box
                val request = Request.Builder()
                        .url("https://hulu1.p.rapidapi.com/search?type=Movie&page=1&title=${editText.text.toString()}")
                        .get()
                        .addHeader("x-rapidapi-key", "f174346b7amsh8eea6a1f1dd6b88p1a04c4jsna38d165ebdcd")
                        .addHeader("x-rapidapi-host", "hulu1.p.rapidapi.com")
                        .build()
                val response = client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        println("failure")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val body = response.body?.string()
                        println("Hulu $body")
                        val testingShit: AmazonAPIResultsItem =
                                gson.fromJson(body, AmazonAPIResultsItem::class.java)

                        results = arrayListOf()


                        if (testingShit.Results != null) {
                            for (movie in testingShit.Results) {
                                results.add(MovieItem(movie.Title, "", 0,movie.Year,0.0, 0.0,"", "Hulu Plus",movie.imdbID))
                            }
                        }

                        //results.add(testingShit.results[0])

                        //I should add a length check here

                    }
                })
            }
            searchResultsRecyclerView.adapter = SearchResultsItemAdapter(results, this)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrListObserver = Observer<ArrayList<MovieItem>> { newArrayList ->
            println("Add Movie Fragment Observer: $newArrayList")
        }

        model.movieArrayList.observe(viewLifecycleOwner, arrListObserver)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        spinnerSelection = parent?.getItemAtPosition(position) as String
        println(spinnerSelection)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}