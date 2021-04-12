package com.example.cellarthesis.ui.subscriptions

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cellarthesis.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SubscriptionsFragment : Fragment() {

    private val model: MainActivityViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_subscriptions, container, false)


        println("onCreateView")

        //subscriptionsList.add(SubscriptionItem("Netflix","3/1/2021","3/31/2021"))

        recyclerView = view.findViewById(R.id.fragment_subscriptions_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)



            view.findViewById<FloatingActionButton>(R.id.subscription_fab).setOnClickListener { view ->
                Navigation.findNavController(view).navigate(R.id.nav_add_subscription)
            }

        return view

    }

    override fun onResume() {
        super.onResume()
        println(model.subscriptionList.value)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrListObserver = Observer<ArrayList<SubscriptionItem>> { newArrayList ->
            if(newArrayList.size > 0) {
                recyclerView.adapter = SubscriptionItemAdapter(newArrayList as List<SubscriptionItem>)
            }

        }

        model.subscriptionList.observe(viewLifecycleOwner, arrListObserver)
    }


}