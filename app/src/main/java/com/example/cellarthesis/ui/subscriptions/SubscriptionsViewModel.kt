package com.naglis.cellar.ui.subscriptions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SubscriptionsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the subscriptions Fragment"
    }
    val text: LiveData<String> = _text
}