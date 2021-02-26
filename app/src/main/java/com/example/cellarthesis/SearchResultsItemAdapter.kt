package com.example.cellarthesis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cellarthesis.ui.addmovie.AddMovieFragment
import com.squareup.picasso.Picasso

class SearchResultsItemAdapter(private val movieItemList: List<MovieItem>, val requestingFragment: AddMovieFragment) : RecyclerView.Adapter<SearchResultsItemAdapter.SearchResultsItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsItemViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.search_results_item, parent, false)
        return SearchResultsItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchResultsItemViewHolder, position: Int) {

        val currentItem = movieItemList[position]


        holder.imageView.setOnClickListener {
            println("test")
        }

        Picasso.get().load(currentItem.img).resize(332,466).into(holder.imageView)
        holder.textView1.text = currentItem.title
        holder.button.setOnClickListener {
                requestingFragment.fetchingData(holder.textView1.text.toString())
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

}