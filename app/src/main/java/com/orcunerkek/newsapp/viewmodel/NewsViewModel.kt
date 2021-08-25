package com.orcunerkek.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orcunerkek.newsapp.model.NewsModel
import com.orcunerkek.newsapp.network.APIService
import com.orcunerkek.newsapp.network.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    private val _newsModel = MutableLiveData<NewsModel>()
    val newsModel: LiveData<NewsModel>
        get() = _newsModel

    fun getNews(from : String, to : String) {
        val apiService: APIService = RetroInstance.getRetroClient()!!.create(APIService::class.java)


        val call: Call<NewsModel> = apiService.getNews( from, to,"ae68088e70d04639b4950bdc9d546924")
        call.enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                _newsModel.postValue(response.body())
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                _newsModel.postValue(null)
            }
        })

    }

    fun getNewsModelObserver(): MutableLiveData<NewsModel>? {
        return _newsModel
    }
}