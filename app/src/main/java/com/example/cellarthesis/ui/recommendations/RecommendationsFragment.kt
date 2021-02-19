package com.example.cellarthesis.ui.recommendations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cellarthesis.R

class RecommendationsFragment : Fragment() {

    private lateinit var recommendationsViewModel: RecommendationsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        recommendationsViewModel = ViewModelProvider(this).get(RecommendationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_recommendations, container, false)
        return root
    }
}