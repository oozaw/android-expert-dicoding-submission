package com.dicoding.gt.favorite.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.core.adapter.NewsAdapter
import com.dicoding.favorite.databinding.ActivityFavoriteBinding
import com.dicoding.gt.detail.DetailActivity
import com.dicoding.gt.di.FavoriteModuleDependencies
import com.dicoding.gt.favorite.di.DaggerFavoriteComponent
import com.dicoding.gt.favorite.di.FavoriteViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {
   private var _binding: ActivityFavoriteBinding? = null
   private val binding get() = _binding!!

   @Inject
   lateinit var factory: FavoriteViewModelFactory

   private val favoriteViewModel: FavoriteViewModel by viewModels { factory }

   override fun onCreate(savedInstanceState: Bundle?) {
      DaggerFavoriteComponent.builder()
         .context(this)
         .appDependencies(
            EntryPointAccessors.fromApplication(
               applicationContext,
               FavoriteModuleDependencies::class.java
            )
         )
         .build()
         .inject(this)
      super.onCreate(savedInstanceState)
      _binding = ActivityFavoriteBinding.inflate(layoutInflater)
      setContentView(binding.root)
      supportActionBar?.title = getString(com.dicoding.gt.R.string.title_favorite)
      supportActionBar?.setDisplayHomeAsUpEnabled(true)

      val newsAdapter = NewsAdapter { news ->
         Intent(this, DetailActivity::class.java).also { intent ->
            intent.putExtra(DetailActivity.EXTRA_NEWS,news)
            intent.putExtra(DetailActivity.EXTRA_KEY, news.key)
            startActivity(intent)
         }
      }

      favoriteViewModel.favNews.observe(this) {
         if (it == null || it.isEmpty()) {
            binding.tvEmpty.visibility = View.VISIBLE
            binding.rvNews.visibility = View.GONE
         } else {
            binding.tvEmpty.visibility = View.GONE
            newsAdapter.differ.submitList(it)
            binding.rvNews.visibility = View.VISIBLE
         }
      }

      binding.rvNews.apply {
         layoutManager = LinearLayoutManager(context)
         setHasFixedSize(true)
         adapter = newsAdapter
      }
   }

   override fun onSupportNavigateUp(): Boolean {
      @Suppress("DEPRECATION")
      onBackPressed()
      return true
   }

   override fun onDestroy() {
      super.onDestroy()
      _binding = null
   }
}