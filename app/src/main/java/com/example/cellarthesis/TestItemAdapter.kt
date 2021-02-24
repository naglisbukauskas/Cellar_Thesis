package com.example.cellarthesis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class TestItemAdapter(private val testItemList: List<TestItem>) : RecyclerView.Adapter<TestItemAdapter.TestItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestItemViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.test_item, parent, false)
        return TestItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TestItemViewHolder, position: Int) {

        val currentItem = testItemList[position]

        println("Picasso Call Starts")

        Picasso.get().load("https://occ-0-2219-2218.1.nflxso.net/dnm/api/v6/evlCitJPPCVCry0BZlEFb5-QjKc/AAAABW-3KdzddLNpQk8HzQhgzN21CRvEHgPzBOQRDDF17My05gvDYi6DctxoFeSW1YsX53Wwx2Ygn0zU-4mVyadygewg3w.jpg?r=efc").into(holder.imageView)
        println("Picasso Call Ends")
        holder.textView1.text = currentItem.title
        holder.textView2.text = currentItem.year
    }

    override fun getItemCount(): Int {
        return testItemList.size
    }

    class TestItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.test_item_image_view)
        val textView1: TextView = itemView.findViewById(R.id.test_item_text_view)
        val textView2: TextView = itemView.findViewById(R.id.test_item_text_view_2)
    }

}