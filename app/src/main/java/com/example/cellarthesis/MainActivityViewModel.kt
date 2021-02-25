package com.example.cellarthesis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class MainActivityViewModel : ViewModel() {

    val movieArrayList: MutableLiveData<ArrayList<TestItem>> by lazy {
        MutableLiveData<ArrayList<TestItem>>(initArrayList)
    }


    private val testItem1: TestItem = TestItem("Movie 1", "test", "2001")
    private val initArrayList = arrayListOf(testItem1)



    fun fetchData(url: String) {
        //asynchronous coroutine
        viewModelScope.launch(Dispatchers.Default) {
            val client = OkHttpClient()

            val request = Request.Builder().url("https://unogsng.p.rapidapi.com/search?query=${url}").get().addHeader("x-rapidapi-key", "f174346b7amsh8eea6a1f1dd6b88p1a04c4jsna38d165ebdcd").addHeader("x-rapidapi-host", "unogsng.p.rapidapi.com").build()

            val response = client.newCall(request).enqueue(object : Callback {

                override fun onFailure(call: Call, e: IOException) {
                    println("failure")
                }

                override fun onResponse(call: Call, response: Response) {
                    println("Successful Response")
                    val body = response.body?.string()
                    println(body)

                    val gson = GsonBuilder().create()
                    val testingShit: APIResultsItem = gson.fromJson(body, APIResultsItem::class.java)

                    val tempArrList: ArrayList<TestItem>? = movieArrayList.value
                    tempArrList!!.add(testingShit.results[0])
                    movieArrayList.postValue(tempArrList)
                    println("Movie Array List: ${movieArrayList.value}")
                }
            })
        }
        println("FetchData Complete")
    }

    fun updateMLD() {

    }
}
