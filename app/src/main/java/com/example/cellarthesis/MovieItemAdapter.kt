package com.example.cellarthesis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MovieItemAdapter(private val movieItemList: List<MovieItem>) : RecyclerView.Adapter<MovieItemAdapter.TestItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestItemViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.test_item, parent, false)
        return TestItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TestItemViewHolder, position: Int) {

        val currentItem = movieItemList[position]

        Picasso.get().load(currentItem.img).resize(332,466).into(holder.imageView)
        holder.textView1.text = currentItem.title
        //holder.textView2.text = currentItem.year
    }

    override fun getItemCount(): Int {
        return movieItemList.size
    }

    class TestItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.test_item_image_view)
        val textView1: TextView = itemView.findViewById(R.id.test_item_text_view)
        //val textView2: TextView = itemView.findViewById(R.id.test_item_text_view_2)
    }

}