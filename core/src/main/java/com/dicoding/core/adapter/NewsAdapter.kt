package com.dicoding.core.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.core.R
import com.dicoding.core.databinding.ItemNewsBinding
import com.dicoding.core.domain.model.News

class NewsAdapter(private val clickListener: (News) -> Unit): RecyclerView.Adapter<NewsAdapter.ListViewHolder>() {

   val differ = AsyncListDiffer(this, DIFF_CALLBACK)


   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ListViewHolder =
   ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false))

   override fun onBindViewHolder(holder: NewsAdapter.ListViewHolder, position: Int) {
      holder.bind(differ.currentList[position])
   }

   override fun getItemCount(): Int = differ.currentList.size

   inner class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
      private val binding = ItemNewsBinding.bind(itemView)

      fun bind(data: News) {
         itemView.setOnClickListener { clickListener(data) }
         with(binding) {
            Glide.with(itemView.context)
               .load(data.thumb)
               .into(ivThumbnail)
            tvTitle.text = data.title
            tvDate.text = data.time
            tvDesc.text = data.desc
         }
      }
   }

   companion object {
      val DIFF_CALLBACK: DiffUtil.ItemCallback<News> =
         object: DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
               return oldItem.key == newItem.key
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
               return oldItem == newItem
            }
         }

   }
}