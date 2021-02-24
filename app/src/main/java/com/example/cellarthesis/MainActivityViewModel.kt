package com.example.cellarthesis

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class MainActivityViewModel: ViewModel() {

    //Hardcoded test just so that I can start to understand livedata a little bit better
    private val testItem1: TestItem = TestItem("Movie 1", "test", "2001")
    private val testItem2: TestItem = TestItem("Movie 2", "test", "2002")
    private val testItem3: TestItem = TestItem("Movie 3", "test", "2003")
    private val initArrayList = arrayListOf(testItem1, testItem2, testItem3)

    //private val mutableTestItemArrayList: MutableLiveData<ArrayList<TestItem>> = MutableLiveData(initArrayList)

    fun getMutableTestItemArrayList(): ArrayList<TestItem>? {
        return mutableTestItemArrayList.value
    }

    val mutableTestItemArrayList: MutableLiveData<ArrayList<TestItem>> by lazy {
        MutableLiveData<ArrayList<TestItem>>(initArrayList)
    }



    //As of right now, this is the main call to fetch data.
    fun fetchData() {
        //asynchronous coroutine
        viewModelScope.launch {
            println("Attempting to fetch JSON")
            val client = OkHttpClient()

            val request = Request.Builder()
                    .url("https://unogsng.p.rapidapi.com/search?query=Inception")
                    .get()
                    .addHeader("x-rapidapi-key", "f174346b7amsh8eea6a1f1dd6b88p1a04c4jsna38d165ebdcd")
                    .addHeader("x-rapidapi-host", "unogsng.p.rapidapi.com")
                    .build()

            val response = client.newCall(request).enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println("failure")
                }

                override fun onResponse(call: Call, response: Response) {
                    println("Successful Response")
                    val body = response.body?.string()
                    println(body)

                    val gson = GsonBuilder().create()
                    val testingShit: APIResultsItem = gson.fromJson(body, APIResultsItem::class.java)
                    val returnVal: TestItem = gson.fromJson(body, TestItem::class.java)

                    println(testingShit)
                    println(returnVal)
                    val tempArrayList: ArrayList<TestItem>? = getMutableTestItemArrayList()
                    tempArrayList?.add(testingShit.results[0])
                    mutableTestItemArrayList.postValue(tempArrayList)
                    println(mutableTestItemArrayList.value)

                }
            })
        }


    }





}
