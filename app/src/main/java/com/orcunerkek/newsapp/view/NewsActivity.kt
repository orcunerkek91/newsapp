package com.orcunerkek.newsapp.view

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.stetho.Stetho
import com.orcunerkek.newsapp.R
import com.orcunerkek.newsapp.adapter.NewsAdapter
import com.orcunerkek.newsapp.model.ArticlesModel
import com.orcunerkek.newsapp.model.NewsModel
import com.orcunerkek.newsapp.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_news.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class NewsActivity : AppCompatActivity() {
    lateinit var newsViewModel: NewsViewModel
    var newsAdapter: NewsAdapter? = null

    var news = NewsModel()
    private lateinit var linearLayoutManager: LinearLayoutManager

    lateinit var tv_choose_date : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        tv_choose_date = findViewById(R.id.tv_choose_date)

        Stetho.initialize(
            Stetho.newInitializerBuilder(this)
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build())

        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        linearLayoutManager = LinearLayoutManager(this.applicationContext)

       // val current = LocalDateTime.now()
       // val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        var date = Date()
        var date2 = Date(date.time-864000000L)  // before 10 days
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val answer: String = formatter.format(date2)

        val c= Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        newsViewModel.getNews(answer,answer)

        newsViewModel.getNewsModelObserver()?.observe(this, Observer {
            if(it!=null) {
                news = it

                linearLayoutManager = LinearLayoutManager(this.applicationContext)
                newsAdapter = NewsAdapter(news.articles as ArrayList<ArticlesModel>)
                rcv_news!!.setHasFixedSize(true)
                rcv_news!!.setLayoutManager(linearLayoutManager)
                rcv_news!!.setAdapter(newsAdapter)


                newsAdapter!!.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        if(news.articles!!.get(position).url!=null) {
                       // Toast.makeText(this@NewsActivity, news.articles!!.get(position).url, Toast.LENGTH_SHORT).show()
                        var intent = Intent(this@NewsActivity, NewsDetailActivity::class.java)
                        intent.putExtra("url",news.articles!!.get(position).url)
                        startActivity(intent) }
                    }

                })
            }

            if(it==null) {
                Toast.makeText(this@NewsActivity, "Günlük Sorgu Limiti Aşımı", Toast.LENGTH_SHORT).show()
            }

        })

        tv_choose_date.setOnClickListener {
            //Toast.makeText(this@NewsActivity, "date picker here", Toast.LENGTH_SHORT).show()
            val dpd=DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->


                var mMonth2=mMonth.toInt()+1
                Toast.makeText(this@NewsActivity, "" +mDay+"/"+mMonth2.toString()+"/"+mYear, Toast.LENGTH_SHORT).show()
                var myString = mYear.toString()+"-"+mMonth2.toString()+"-"+mDay.toString()

                newsViewModel.getNews(myString,myString)

            }, year,month,day)
            dpd.datePicker.maxDate=System.currentTimeMillis()
            dpd.show()

        }



    }
}