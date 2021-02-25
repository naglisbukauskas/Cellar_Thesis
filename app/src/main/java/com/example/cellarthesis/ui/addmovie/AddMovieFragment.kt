package com.example.cellarthesis.ui.addmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import com.example.cellarthesis.*
import com.example.cellarthesis.R


class AddMovieFragment: Fragment() {

    private val model: MainActivityViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_movie, container, false)

        val editText: EditText = view.findViewById(R.id.fragmentMovieEditText)
        val button: Button = view.findViewById(R.id.fragmentMovieAddButton)

        button.setOnClickListener {
            model.fetchData(editText.text.toString())
        }

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrListObserver = Observer<ArrayList<TestItem>> { newArrayList ->
            println("Add Movie Fragment Observer: $newArrayList")
        }

        model.movieArrayList.observe(viewLifecycleOwner, arrListObserver)
    }

}