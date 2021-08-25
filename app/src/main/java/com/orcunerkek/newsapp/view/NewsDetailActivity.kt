package com.orcunerkek.newsapp.view

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.orcunerkek.newsapp.R

class NewsDetailActivity : AppCompatActivity()  {

    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        val bundle :Bundle ?=intent.extras
        if (bundle!=null){
            val message = bundle.getString("url")
            webView = findViewById(R.id.webView)
            webView.settings.setJavaScriptEnabled(true)
            webView.settings.setLoadWithOverviewMode(true)
            webView.settings.setUseWideViewPort(true)
            webView.settings.setDomStorageEnabled(true);

          /*  webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    if (message != null) {
                        view?.loadUrl(message)
                    }
                    return true
                }
            }*/

            if (message != null) {
                webView.loadUrl(message)
            }
        }



    }
}