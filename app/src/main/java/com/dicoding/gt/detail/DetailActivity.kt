package com.dicoding.gt.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dicoding.core.data.Status
import com.dicoding.core.domain.model.News
import com.dicoding.core.utils.DataMapper
import com.dicoding.gt.R
import com.dicoding.gt.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

   private var _binding: ActivityDetailBinding? = null
   private val binding get() = _binding!!

   private val detailViewModel: DetailViewModel by viewModels()

   private lateinit var news: News

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      _binding = ActivityDetailBinding.inflate(layoutInflater)
      setContentView(binding.root)
      supportActionBar?.title = getString(R.string.detail_title)

      @Suppress("DEPRECATION")
      intent.getParcelableExtra<News>(EXTRA_NEWS)?.let { news = it }
      Log.d(TAG, "onCreate: $news")

      detailViewModel.detailNews(news, news.key as String).observe(this) {
         when(it) {
            is Status.Success -> {
               setupView(it.data)
               it.data?.let { result ->
                  news = DataMapper.updateDomain(news, result)
                  Log.d(TAG, "updateDom: $news")
               }
            }
            is Status.Loading -> showLoading(true)
            is Status.Error -> {
               Toast.makeText(applicationContext, getString(R.string.something_wrong), Toast.LENGTH_LONG).show()
               setupView(it.data)
            }
         }
      }
   }

   private fun setupView(data: News?) {
      if (data == null) return
      showLoading(false)

      val thumb = if (data.thumb != null) data.thumb else news.thumb
      val time = if (data.time != null) data.time else news.time

      Glide.with(this)
         .load(thumb)
         .into(binding.ivThumbnail)
      binding.tvTitle.text = data.title
      binding.tvAuthor.text = data.author
      binding.tvDate.text = time
      binding.tvContent.text = data.content.toString()
      setStatusFav(data.isFavorite)
      binding.fabFavorite.setOnClickListener {
         val stateFav = !data.isFavorite
         detailViewModel.setFavoriteNews(news, stateFav)
         setStatusFav(stateFav)
      }
   }

   private fun setStatusFav(isFav: Boolean) {
      if (isFav) {
         binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_24))
      } else {
         binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_24))
      }
   }

   private fun showLoading(state: Boolean) {
      binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
      binding.layContent.visibility = if (state) View.GONE else View.VISIBLE
      binding.fabFavorite.visibility = if (state) View.GONE else View.VISIBLE
   }

   override fun onDestroy() {
      super.onDestroy()
      _binding = null
   }

   companion object {
      const val EXTRA_KEY = "extra_key"
      const val EXTRA_NEWS = "extra_news"
      const val TAG = "DetailActivity"
   }
}