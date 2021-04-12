package com.example.cellarthesis.ui.recommendations

import android.os.Bundle
import android.view.Display
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cellarthesis.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import org.w3c.dom.Text
import java.io.IOException

class RecommendationsFragment : Fragment() {

    private val model: MainActivityViewModel by activityViewModels()
    private lateinit var gson: Gson

    var results: ArrayList<RecMovieItem> = arrayListOf()
    var results1: ArrayList<RecMovieItem> = arrayListOf()
    var results2: ArrayList<RecMovieItem> = arrayListOf()
    var results3: ArrayList<RecMovieItem> = arrayListOf()
    var results4: ArrayList<RecMovieItem> = arrayListOf()
    var results5: ArrayList<RecMovieItem> = arrayListOf()

    private var nGenres: ArrayList<RecTrackItem> = arrayListOf()
    private var aGenres: ArrayList<RecTrackItem> = arrayListOf()
    private var hpGenres: ArrayList<RecTrackItem> = arrayListOf()

    private lateinit var recyclerView1: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var recyclerView3: RecyclerView
    private lateinit var recyclerView4: RecyclerView
    private lateinit var recyclerView5: RecyclerView
    private lateinit var recyclerView6: RecyclerView

    private lateinit var recHead1: TextView
    private lateinit var recHead2: TextView
    private lateinit var recHead3: TextView
    private lateinit var recHead4: TextView
    private lateinit var recHead5: TextView
    private lateinit var recHead6: TextView

    private var recHeadText1: String = ""
    private var recHeadText2: String = ""
    private var recHeadText3: String = ""
    private var recHeadText4: String = ""
    private var recHeadText5: String = ""
    private var recHeadText6: String = ""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recommendations, container, false)
        gson = GsonBuilder().create()

        generateGenreID()

        recHead1 = view.findViewById(R.id.rec_heading_1)
        recHead2 = view.findViewById(R.id.rec_heading_2)
        recHead3 = view.findViewById(R.id.rec_heading_3)
        recHead4 = view.findViewById(R.id.rec_heading_4)
        recHead5 = view.findViewById(R.id.rec_heading_5)
        recHead6 = view.findViewById(R.id.rec_heading_6)

        recyclerView1 = view.findViewById(R.id.recommendation_recycler_view_1)
        recyclerView1.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        recyclerView2 = view.findViewById(R.id.recommendation_recycler_view_2)
        recyclerView2.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        recyclerView3 = view.findViewById(R.id.recommendation_recycler_view_3)
        recyclerView3.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        recyclerView4 = view.findViewById(R.id.recommendation_recycler_view_4)
        recyclerView4.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        recyclerView5 = view.findViewById(R.id.recommendation_recycler_view_5)
        recyclerView5.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        recyclerView6 = view.findViewById(R.id.recommendation_recycler_view_6)
        recyclerView6.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        view.findViewById<Button>(R.id.recommendationGenerateButton).setOnClickListener {
            generateRecommendations()
        }



        return view
    }

    private fun generateGenreID() {
        for(movie in model.movieArrayList.value!!) {
            if(movie.platform == "Netflix") {
                lifecycleScope.launch(Dispatchers.Default) {
                    val client = OkHttpClient()

                    val request = Request.Builder()
                            .url("https://imdb8.p.rapidapi.com/title/get-genres?tconst=${movie.imdbid}")
                            .get()
                            .addHeader("x-rapidapi-key", "f174346b7amsh8eea6a1f1dd6b88p1a04c4jsna38d165ebdcd")
                            .addHeader("x-rapidapi-host", "imdb8.p.rapidapi.com")
                            .build()
                    val response = client.newCall(request).enqueue(object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
                            println("failure")
                        }

                        override fun onResponse(call: Call, response: Response) {
                            val body = response.body?.string()
                            println(body)
                            val testingShit: Array<String> = gson.fromJson(body, Array<String>::class.java)

                            for(i in testingShit.indices) {
                                when (testingShit[i]) {
                                    "Biography" -> testingShit[i] = "1"
                                    "Film Noir" -> testingShit[i] = "2"
                                    "Game Show" -> testingShit[i] = "3"
                                    "Musical" -> testingShit[i] = "4"
                                    "Sport" -> testingShit[i] = "5"
                                    "Short" -> testingShit[i] = "6"
                                    "Adult" -> testingShit[i] = "7"
                                    "Adventure" -> testingShit[i] = "12"
                                    "Fantasy" -> testingShit[i] = "14"
                                    "Animation" -> testingShit[i] = "16"
                                    "Drama" -> testingShit[i] = "18"
                                    "Horror" -> testingShit[i] = "27"
                                    "Action" -> testingShit[i] = "28"
                                    "Comedy" -> testingShit[i] = "35"
                                    "History" -> testingShit[i] = "36"
                                    "Western" -> testingShit[i] = "37"
                                    "Thriller" -> testingShit[i] = "53"
                                    "Crime" -> testingShit[i] = "80"
                                    "Documentary" -> testingShit[i] = "99"
                                    "Sci-Fi" -> testingShit[i] = "878"
                                    "Mystery" -> testingShit[i] = "9648"
                                    "Music" -> testingShit[i] = "10402"
                                    "Romance" -> testingShit[i] = "10749"
                                    "Family" -> testingShit[i] = "10751"
                                    "War" -> testingShit[i] = "10752"
                                    "News" -> testingShit[i] = "10763"
                                    "Reality" -> testingShit[i] = "10764"
                                    "Talk Show" -> testingShit[i] = "10767"
                                }
                            }

                            nGenres.add(RecTrackItem(movie.title, movie.platform, testingShit))

                        }
                    })
                }
            } else if (movie.platform == "Amazon") {
                lifecycleScope.launch(Dispatchers.Default) {
                    val client = OkHttpClient()

                    val request = Request.Builder()
                            .url("https://imdb8.p.rapidapi.com/title/get-genres?tconst=${movie.imdbid}")
                            .get()
                            .addHeader("x-rapidapi-key", "f174346b7amsh8eea6a1f1dd6b88p1a04c4jsna38d165ebdcd")
                            .addHeader("x-rapidapi-host", "imdb8.p.rapidapi.com")
                            .build()
                    val response = client.newCall(request).enqueue(object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
                            println("failure")
                        }

                        override fun onResponse(call: Call, response: Response) {
                            val body = response.body?.string()
                            println(body)
                            val testingShit: Array<String> = gson.fromJson(body, Array<String>::class.java)

                            for(i in testingShit.indices) {
                                when (testingShit[i]) {
                                    "Biography" -> testingShit[i] = "1"
                                    "Film Noir" -> testingShit[i] = "2"
                                    "Game Show" -> testingShit[i] = "3"
                                    "Musical" -> testingShit[i] = "4"
                                    "Sport" -> testingShit[i] = "5"
                                    "Short" -> testingShit[i] = "6"
                                    "Adult" -> testingShit[i] = "7"
                                    "Adventure" -> testingShit[i] = "12"
                                    "Fantasy" -> testingShit[i] = "14"
                                    "Animation" -> testingShit[i] = "16"
                                    "Drama" -> testingShit[i] = "18"
                                    "Horror" -> testingShit[i] = "27"
                                    "Action" -> testingShit[i] = "28"
                                    "Comedy" -> testingShit[i] = "35"
                                    "History" -> testingShit[i] = "36"
                                    "Western" -> testingShit[i] = "37"
                                    "Thriller" -> testingShit[i] = "53"
                                    "Crime" -> testingShit[i] = "80"
                                    "Documentary" -> testingShit[i] = "99"
                                    "Sci-Fi" -> testingShit[i] = "878"
                                    "Mystery" -> testingShit[i] = "9648"
                                    "Music" -> testingShit[i] = "10402"
                                    "Romance" -> testingShit[i] = "10749"
                                    "Family" -> testingShit[i] = "10751"
                                    "War" -> testingShit[i] = "10752"
                                    "News" -> testingShit[i] = "10763"
                                    "Reality" -> testingShit[i] = "10764"
                                    "Talk Show" -> testingShit[i] = "10767"
                                }
                            }

                            aGenres.add(RecTrackItem(movie.title, movie.platform, testingShit))

                        }
                    })
                }
            } else if (movie.platform == "Hulu Plus") {
                lifecycleScope.launch(Dispatchers.Default) {
                    val client = OkHttpClient()

                    val request = Request.Builder()
                            .url("https://imdb8.p.rapidapi.com/title/get-genres?tconst=${movie.imdbid}")
                            .get()
                            .addHeader("x-rapidapi-key", "f174346b7amsh8eea6a1f1dd6b88p1a04c4jsna38d165ebdcd")
                            .addHeader("x-rapidapi-host", "imdb8.p.rapidapi.com")
                            .build()
                    val response = client.newCall(request).enqueue(object : Callback {
                        override fun onFailure(call: Call, e: IOException) {
                            println("failure")
                        }

                        override fun onResponse(call: Call, response: Response) {
                            val body = response.body?.string()
                            println(body)
                            val testingShit: Array<String> = gson.fromJson(body, Array<String>::class.java)

                            for(i in testingShit.indices) {
                                when (testingShit[i]) {
                                    "Biography" -> testingShit[i] = "1"
                                    "Film Noir" -> testingShit[i] = "2"
                                    "Game Show" -> testingShit[i] = "3"
                                    "Musical" -> testingShit[i] = "4"
                                    "Sport" -> testingShit[i] = "5"
                                    "Short" -> testingShit[i] = "6"
                                    "Adult" -> testingShit[i] = "7"
                                    "Adventure" -> testingShit[i] = "12"
                                    "Fantasy" -> testingShit[i] = "14"
                                    "Animation" -> testingShit[i] = "16"
                                    "Drama" -> testingShit[i] = "18"
                                    "Horror" -> testingShit[i] = "27"
                                    "Action" -> testingShit[i] = "28"
                                    "Comedy" -> testingShit[i] = "35"
                                    "History" -> testingShit[i] = "36"
                                    "Western" -> testingShit[i] = "37"
                                    "Thriller" -> testingShit[i] = "53"
                                    "Crime" -> testingShit[i] = "80"
                                    "Documentary" -> testingShit[i] = "99"
                                    "Sci-Fi" -> testingShit[i] = "878"
                                    "Mystery" -> testingShit[i] = "9648"
                                    "Music" -> testingShit[i] = "10402"
                                    "Romance" -> testingShit[i] = "10749"
                                    "Family" -> testingShit[i] = "10751"
                                    "War" -> testingShit[i] = "10752"
                                    "News" -> testingShit[i] = "10763"
                                    "Reality" -> testingShit[i] = "10764"
                                    "Talk Show" -> testingShit[i] = "10767"
                                }
                            }


                            hpGenres.add(RecTrackItem(movie.title, movie.platform, testingShit))

                        }
                    })
                }
            }
        }
    }

    private fun generateRecommendations() {
        lifecycleScope.launch(Dispatchers.Default) {
            if(nGenres.isNotEmpty()) {
                recHeadText1 = "Since you liked ${nGenres[0].title} on Netflix, check these out on Amazon"

                recHeadText2 = "Since you liked ${nGenres[0].title} on Netflix, check these out on Hulu"


                val client = OkHttpClient()
                //fetch movies from the text box
                val request = Request.Builder()
                        .url("https://streaming-availability.p.rapidapi.com/search/ultra?country=us&service=prime&type=movie&order_by=imdb_rating&page=1&genres=${nGenres[0].genres[0]}&desc=true&language=en&min_imdb_rating=70")
                        .get()
                        .addHeader("x-rapidapi-key", "f174346b7amsh8eea6a1f1dd6b88p1a04c4jsna38d165ebdcd")
                        .addHeader("x-rapidapi-host", "streaming-availability.p.rapidapi.com")
                        .build()
                val response = client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        println("failure")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val body = response.body?.string()
                        println("Streaming Availability")
                        println(body)

                        val testingShit: RecResultsItem = gson.fromJson(body, RecResultsItem::class.java)
                        println(testingShit)

                        if(testingShit.results != null) {
                            for (movie in testingShit.results) {
                                results.add(movie)
                            }
                        }
                    }
                })

                val request1 = Request.Builder()
                        .url("https://streaming-availability.p.rapidapi.com/search/ultra?country=us&service=hulu&type=movie&order_by=imdb_rating&page=1&genres=${nGenres[0].genres[0]}&desc=true&language=en&min_imdb_rating=70")
                        .get()
                        .addHeader("x-rapidapi-key", "f174346b7amsh8eea6a1f1dd6b88p1a04c4jsna38d165ebdcd")
                        .addHeader("x-rapidapi-host", "streaming-availability.p.rapidapi.com")
                        .build()
                val response1 = client.newCall(request1).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        println("failure")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val body = response.body?.string()
                        println("Streaming Availability")
                        println(body)

                        val testingShit: RecResultsItem = gson.fromJson(body, RecResultsItem::class.java)
                        println(testingShit)

                        if(testingShit.results != null) {
                            for (movie in testingShit.results) {
                                results1.add(movie)
                            }
                        }
                    }
                })
            }

            if(aGenres.isNotEmpty()) {
                recHeadText3 = "Since you liked ${aGenres[0].title} on Amazon, check these out on Netflix"

                recHeadText4 = "Since you liked ${aGenres[0].title} on Amazon, check these out on Hulu"


                val client = OkHttpClient()
                //fetch movies from the text box
                val request = Request.Builder()
                        .url("https://streaming-availability.p.rapidapi.com/search/ultra?country=us&service=netflix&type=movie&order_by=imdb_rating&page=1&genres=${aGenres[0].genres[0]}&desc=true&language=en&min_imdb_rating=70")
                        .get()
                        .addHeader("x-rapidapi-key", "f174346b7amsh8eea6a1f1dd6b88p1a04c4jsna38d165ebdcd")
                        .addHeader("x-rapidapi-host", "streaming-availability.p.rapidapi.com")
                        .build()
                val response = client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        println("failure")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val body = response.body?.string()
                        println("Streaming Availability")
                        println(body)

                        val testingShit: RecResultsItem = gson.fromJson(body, RecResultsItem::class.java)
                        println(testingShit)

                        if(testingShit.results != null) {
                            for (movie in testingShit.results) {
                                results2.add(movie)
                            }
                        }
                    }
                })

                val request1 = Request.Builder()
                        .url("https://streaming-availability.p.rapidapi.com/search/ultra?country=us&service=hulu&type=movie&order_by=imdb_rating&page=1&genres=${aGenres[0].genres[0]}&desc=true&language=en&min_imdb_rating=70")
                        .get()
                        .addHeader("x-rapidapi-key", "f174346b7amsh8eea6a1f1dd6b88p1a04c4jsna38d165ebdcd")
                        .addHeader("x-rapidapi-host", "streaming-availability.p.rapidapi.com")
                        .build()
                val response1 = client.newCall(request1).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        println("failure")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val body = response.body?.string()
                        println("Streaming Availability")
                        println(body)

                        val testingShit: RecResultsItem = gson.fromJson(body, RecResultsItem::class.java)
                        println(testingShit)

                        if(testingShit.results != null) {
                            for (movie in testingShit.results) {
                                results3.add(movie)
                            }
                        }
                    }
                })
            }

            if(hpGenres.isNotEmpty()) {
                recHeadText5 = "Since you liked ${hpGenres[0].title} on Hulu, check these out on Netflix"

                recHeadText6 = "Since you liked ${hpGenres[0].title} on Hulu, check these out on Amazon"


                val client = OkHttpClient()
                //fetch movies from the text box
                val request = Request.Builder()
                        .url("https://streaming-availability.p.rapidapi.com/search/ultra?country=us&service=netflix&type=movie&order_by=imdb_rating&page=1&genres=${nGenres[0].genres[0]}&desc=true&language=en&min_imdb_rating=70")
                        .get()
                        .addHeader("x-rapidapi-key", "f174346b7amsh8eea6a1f1dd6b88p1a04c4jsna38d165ebdcd")
                        .addHeader("x-rapidapi-host", "streaming-availability.p.rapidapi.com")
                        .build()
                val response = client.newCall(request).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        println("failure")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val body = response.body?.string()
                        println("Streaming Availability")
                        println(body)

                        val testingShit: RecResultsItem = gson.fromJson(body, RecResultsItem::class.java)
                        println(testingShit)

                        if(testingShit.results != null) {
                            for (movie in testingShit.results) {
                                results4.add(movie)
                            }
                        }
                    }
                })

                val request1 = Request.Builder()
                        .url("https://streaming-availability.p.rapidapi.com/search/ultra?country=us&service=prime&type=movie&order_by=imdb_rating&page=1&genres=${nGenres[0].genres[0]}&desc=true&language=en&min_imdb_rating=70")
                        .get()
                        .addHeader("x-rapidapi-key", "f174346b7amsh8eea6a1f1dd6b88p1a04c4jsna38d165ebdcd")
                        .addHeader("x-rapidapi-host", "streaming-availability.p.rapidapi.com")
                        .build()
                val response1 = client.newCall(request1).enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        println("failure")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val body = response.body?.string()
                        println("Streaming Availability")
                        println(body)

                        val testingShit: RecResultsItem = gson.fromJson(body, RecResultsItem::class.java)
                        println(testingShit)

                        if(testingShit.results != null) {
                            for (movie in testingShit.results) {
                                results5.add(movie)
                            }
                        }
                    }
                })
            }


        }

        recHead1.text = recHeadText1
        recHead2.text = recHeadText2
        recHead3.text = recHeadText3
        recHead4.text = recHeadText4
        recHead5.text = recHeadText5
        recHead6.text = recHeadText6

        recyclerView1.adapter = RecMovieItemAdapter(results)
        recyclerView2.adapter = RecMovieItemAdapter(results1)
        recyclerView3.adapter = RecMovieItemAdapter(results2)
        recyclerView4.adapter = RecMovieItemAdapter(results3)
        recyclerView5.adapter = RecMovieItemAdapter(results4)
        recyclerView6.adapter = RecMovieItemAdapter(results5)



    }

    data class RecTrackItem(val title: String, val platform: String, val genres: Array<String>)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrListObserver = Observer<ArrayList<MovieItem>> { newArrayList ->
            println("Recommendation Fragment Observer: $newArrayList")
        }

        model.movieArrayList.observe(viewLifecycleOwner, arrListObserver)
    }

}
