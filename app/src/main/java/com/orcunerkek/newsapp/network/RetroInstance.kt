package com.orcunerkek.newsapp.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object {
        var BASE_URL = "https://newsapi.org/"
        private var retrofit: Retrofit? = null

        fun getRetroClient(): Retrofit? {
            val okClient = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()
            if (retrofit == null) {
                retrofit = Retrofit.Builder().client(okClient).baseUrl(BASE_URL).addConverterFactory(
                    GsonConverterFactory.create()).build()
            }
            return retrofit
        }
    }
}