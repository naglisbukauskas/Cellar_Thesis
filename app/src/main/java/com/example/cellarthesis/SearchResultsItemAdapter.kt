package com.example.cellarthesis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cellarthesis.ui.addmovie.AddMovieFragment
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import okhttp3.*
import java.io.IOException

class SearchResultsItemAdapter(private val movieItemList: List<MovieItem>, val requestingFragment: AddMovieFragment) : RecyclerView.Adapter<SearchResultsItemAdapter.SearchResultsItemViewHolder>() {

    private var gson = GsonBuilder().create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsItemViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.search_results_item, parent, false)
        return SearchResultsItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchResultsItemViewHolder, position: Int) {

        val currentItem = movieItemList[position]




        holder.imageView.setOnClickListener {
            println("test")
        }
        if(currentItem.img == "") {
            Picasso.get().load("https://www.salonlfc.com/wp-content/uploads/2018/01/image-not-found-scaled.png").resize(332, 466).into(holder.imageView)
        } else {
            Picasso.get().load(currentItem.img).resize(332, 466).into(holder.imageView)
        }
        holder.textView1.text = currentItem.title
        holder.button.setOnClickListener {
                requestingFragment.fetchingData(holder.textView1.text.toString(), currentItem.platform)
        }
    }

    override fun getItemCount(): Int {
        return movieItemList.size
    }

    class SearchResultsItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.search_results_item_image_view)
        val textView1: TextView = itemView.findViewById(R.id.search_results_item_text_view)
        val button: Button = itemView.findViewById(R.id.search_results_add_button)

    }

    private fun getGenreOfAddedItem(movie: MovieItem) {
            val client = OkHttpClient()
            val request1 = Request.Builder()
                    .url("https://unogsng.p.rapidapi.com/titlegenres?netflixid=${movie.nfid}")
                    .get()
                    .addHeader("x-rapidapi-key", "f174346b7amsh8eea6a1f1dd6b88p1a04c4jsna38d165ebdcd")
                    .addHeader("x-rapidapi-host", "unogsng.p.rapidapi.com")
                    .build()
            val response1 = client.newCall(request1).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println("genre get failure")
                }

                override fun onResponse(call: Call, response: Response) {
                    val body1 = response.body?.string()
                    println("GENRE GET SUCCEEDED")
                    if (body1 != null) {
                        val genre: GenreResultsItem = gson.fromJson(body1, GenreResultsItem::class.java)
                        //movie.genre = genre.results[0].genre
                    }
                }
            })
        }

    }
