package com.orcunerkek.newsapp.network


import com.orcunerkek.newsapp.model.NewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("v2/everything?q=football")
    fun getNews(@Query("from") from: String?,@Query("to") to: String?, @Query("apiKey") apiKey: String?): Call<NewsModel>


}