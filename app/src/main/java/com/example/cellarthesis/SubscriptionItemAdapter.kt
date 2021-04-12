package com.example.cellarthesis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class SubscriptionItemAdapter(private val subscriptionItemList: List<SubscriptionItem>) : RecyclerView.Adapter<SubscriptionItemAdapter.SubscriptionItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionItemViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.subscription_item, parent, false)
        return SubscriptionItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SubscriptionItemViewHolder, position: Int) {

        val currentItem = subscriptionItemList[position]

        holder.textView1.text = currentItem.serviceName
        holder.textView2.text = currentItem.subStartDate
        holder.textView3.text = currentItem.subEndDate
        //holder.textView2.text = currentItem.year
    }

    override fun getItemCount(): Int {
        return subscriptionItemList.size
    }

    class SubscriptionItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.subscription_item_service_name)
        val textView2: TextView = itemView.findViewById(R.id.subscription_item_service_start_date)
        val textView3: TextView = itemView.findViewById(R.id.subscription_item_service_end_date)
    }

}