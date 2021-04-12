package com.example.cellarthesis.ui.addsubscription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.cellarthesis.*

class AddSubscriptionFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    var subscriptionsList: ArrayList<SubscriptionItem> = arrayListOf()


    private val model: MainActivityViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_add_subscription, container, false)

        val serviceName = root.findViewById<EditText>(R.id.sub_service_name_edit_text)
        val serviceStartDate = root.findViewById<EditText>(R.id.sub_service_start_date_edit_text)
        val serviceEndDate = root.findViewById<EditText>(R.id.sub_service_end_date_edit_text)
        val button = root.findViewById<Button>(R.id.add_subscription_activity_button)

        button.setOnClickListener {
            println(serviceName.text)
            println(serviceStartDate.text)
            println(serviceEndDate.text)
            model.addSubscription(serviceName.text.toString(), serviceStartDate.text.toString(), serviceEndDate.text.toString())
        }





        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrListObserver = Observer<ArrayList<SubscriptionItem>> { newArrayList ->
            println("Change Observed")
        }

        model.subscriptionList.observe(viewLifecycleOwner, arrListObserver)

    }


}