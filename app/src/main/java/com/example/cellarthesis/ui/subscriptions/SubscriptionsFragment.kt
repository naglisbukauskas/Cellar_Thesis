package com.example.cellarthesis.ui.subscriptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cellarthesis.R
import com.naglis.cellar.ui.subscriptions.SubscriptionsViewModel

class SubscriptionsFragment : Fragment() {

    private lateinit var subscriptionsViewModel: SubscriptionsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        subscriptionsViewModel = ViewModelProvider(this).get(SubscriptionsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_subscriptions, container, false)
        return root
    }
}