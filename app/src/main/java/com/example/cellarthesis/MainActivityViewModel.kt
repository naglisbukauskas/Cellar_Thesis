package com.example.cellarthesis

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class MainActivityViewModel : ViewModel() {

    val movieArrayList: MutableLiveData<ArrayList<MovieItem>> by lazy {
       //MutableLiveData<ArrayList<MovieItem>>(initArrayList)
        MutableLiveData<ArrayList<MovieItem>>()
    }

    val subscriptionList: MutableLiveData<ArrayList<SubscriptionItem>> by lazy {
        MutableLiveData<ArrayList<SubscriptionItem>>()
    }


    private val movieItem1: MovieItem = MovieItem("Movie 1", "test", 0, "2001", 0.0, 0.0, "", "","")
    private val initArrayList = arrayListOf(movieItem1)



    fun fetchData(url: String, platform: String) {
        println("Fetch Data Url: $url")
        if(platform == "Netflix") {
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

                        testingShit.results[0].platform = "Netflix"

                                //I should add a length check here
                        if (movieArrayList.value == null) {
                            val tempArrList: ArrayList<MovieItem> = arrayListOf(testingShit.results[0])
                            movieArrayList.postValue(tempArrList)
                        } else {
                            val tempArrList: ArrayList<MovieItem>? = movieArrayList.value
                            tempArrList!!.add(testingShit.results[0])
                            movieArrayList.postValue(tempArrList)
                            println("Movie Array List: ${movieArrayList.value}")
                        }
                    }
                })
            }
        } else if (platform == "Amazon") {
            viewModelScope.launch(Dispatchers.Default) {
                val client = OkHttpClient()

                val request = Request.Builder()
                        .url("https://streamzui-streamzui-v1.p.rapidapi.com/search?page=1&country=us&type=Movie&title=${url}")
                        .get()
                        .addHeader("x-rapidapi-key", "f174346b7amsh8eea6a1f1dd6b88p1a04c4jsna38d165ebdcd")
                        .addHeader("x-rapidapi-host", "streamzui-streamzui-v1.p.rapidapi.com")
                        .build()

                val response = client.newCall(request).enqueue(object : Callback {

                    override fun onFailure(call: Call, e: IOException) {
                        println("failure")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        println("Successful Response")
                        val body = response.body?.string()
                        println(body)

                        val gson = GsonBuilder().create()
                        val testingShit: AmazonAPIResultsItem =
                                gson.fromJson(body, AmazonAPIResultsItem::class.java)

                        val amazonResults: MovieItem = MovieItem(testingShit.Results[0].Title, "", 0,testingShit.Results[0].Year,0.0, 0.0,"", "Amazon",testingShit.Results[0].imdbID)

                        //I should add a length check here
                        if (movieArrayList.value == null) {
                            val tempArrList: ArrayList<MovieItem> = arrayListOf(amazonResults)
                            movieArrayList.postValue(tempArrList)
                        } else {
                            val tempArrList: ArrayList<MovieItem>? = movieArrayList.value
                            tempArrList!!.add(amazonResults)
                            movieArrayList.postValue(tempArrList)
                            println("Movie Array List: ${movieArrayList.value}")
                        }
                    }
                })
            }
        } else if (platform == "Hulu Plus") {
            viewModelScope.launch(Dispatchers.Default) {
                val client = OkHttpClient()

                val request = Request.Builder()
                        .url("https://hulu1.p.rapidapi.com/search?type=Movie&page=1&title=${url}")
                        .get()
                        .addHeader("x-rapidapi-key", "f174346b7amsh8eea6a1f1dd6b88p1a04c4jsna38d165ebdcd")
                        .addHeader("x-rapidapi-host", "hulu1.p.rapidapi.com")
                        .build()

                val response = client.newCall(request).enqueue(object : Callback {

                    override fun onFailure(call: Call, e: IOException) {
                        println("failure")
                    }

                    override fun onResponse(call: Call, response: Response) {
                        println("Successful Response")
                        val body = response.body?.string()
                        println(body)

                        val gson = GsonBuilder().create()
                        val testingShit: AmazonAPIResultsItem =
                                gson.fromJson(body, AmazonAPIResultsItem::class.java)

                        val amazonResults: MovieItem = MovieItem(testingShit.Results[0].Title, "", 0,testingShit.Results[0].Year,0.0, 0.0,"", "Hulu Plus",testingShit.Results[0].imdbID)

                        //I should add a length check here
                        if (movieArrayList.value == null) {
                            val tempArrList: ArrayList<MovieItem> = arrayListOf(amazonResults)
                            movieArrayList.postValue(tempArrList)
                        } else {
                            val tempArrList: ArrayList<MovieItem>? = movieArrayList.value
                            tempArrList!!.add(amazonResults)
                            movieArrayList.postValue(tempArrList)
                            println("Movie Array List: ${movieArrayList.value}")
                        }
                    }
                })
            }
        }
        println("FetchData Complete")
    }

    fun addSubscription(serviceName: String, subStartDate: String, subEndDate: String) {
        if(subscriptionList.value == null) {
            val tempArrList: ArrayList<SubscriptionItem> = arrayListOf(SubscriptionItem(serviceName, subStartDate, subEndDate))
            subscriptionList.postValue(tempArrList)
        } else {
            val tempArrList: ArrayList<SubscriptionItem>? = subscriptionList.value
            tempArrList!!.add(SubscriptionItem(serviceName, subStartDate, subEndDate))
            subscriptionList.postValue(tempArrList)
        }
    }

}
