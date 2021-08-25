package com.orcunerkek.newsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orcunerkek.newsapp.R
import com.orcunerkek.newsapp.model.ArticlesModel
import com.orcunerkek.newsapp.model.NewsModel

class NewsAdapter(private val news: ArrayList<ArticlesModel>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var mListener: OnItemClickListener? = null
    var context : Context? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }


    class NewsViewHolder(itemView: View, listener: OnItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        var tv_title : TextView
        var tv_content : TextView
        var image_of_news : ImageView


        init {
            tv_title = itemView.findViewById(R.id.tv_title)
            tv_content = itemView.findViewById(R.id.tv_content)
            image_of_news = itemView.findViewById(R.id.image_of_news)

            itemView.setOnClickListener {
                if (listener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        context = parent.context
        return NewsViewHolder(v, mListener)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val exam = news[position]
        /*     holder.tv_exam_name.setText(exam.exam_name)
             holder.tv_exam_date.setText(exam.exam_date)*/
        holder.tv_content.setText(news.get(position).content.toString())
        holder.tv_title.setText(news.get(position).title.toString())
        Glide.with(context!!)
            .load(news.get(position).urlToImage.toString())
            .fitCenter()
            .into(holder.image_of_news)




    }

    override fun getItemCount(): Int {
        return news.size
    }
}