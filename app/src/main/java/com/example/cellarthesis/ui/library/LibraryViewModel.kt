package com.example.cellarthesis.ui.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class LibraryViewModel : ViewModel() {

    //This is where handling the data happens. Need to use coroutine, because if the viewmodel provides data to the
    //fragment when the fragment is dead, then the fragment crashes. Mapping is done within the coroutine that is inside of

    //Thread the api call here through co-routines

   fun fetchData() {
       viewModelScope.launch {
           println("Attempting to fetch JSON")

           val url = "https://unogsng.p.rapidapi.com/genres"

           val client = OkHttpClient()

           val request = Request.Builder()
                   .url("https://unogsng.p.rapidapi.com/genres")
                   .get()
                   .addHeader("x-rapidapi-key", "f174346b7amsh8eea6a1f1dd6b88p1a04c4jsna38d165ebdcd")
                   .addHeader("x-rapidapi-host", "unogsng.p.rapidapi.com")
                   .build()

           val response = client.newCall(request).enqueue(object: Callback {
               override fun onFailure(call: Call, e: IOException) {
                   println("failure")
               }

               override fun onResponse(call: Call, response: Response) {
                   val body = response.body?.string()
                   println(body)
               }
           })
       }

   }
}