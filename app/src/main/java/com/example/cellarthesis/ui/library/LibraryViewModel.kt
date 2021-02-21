package com.example.cellarthesis.ui.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LibraryViewModel : ViewModel() {

    //This is where handling the data happens. Need to use coroutine, because if the viewmodel provides data to the
    //fragment when the fragment is dead, then the fragment crashes. Mapping is done within the coroutine that is inside of

    //Thread the api call here through co-routines

    private val _text = MutableLiveData<String>().apply {
        value = "This is the library Fragment"
    }
    val text: LiveData<String> = _text

    fun returnNum(): Int {
        return 6
    }
}