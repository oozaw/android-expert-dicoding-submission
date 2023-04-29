package com.dicoding.gt.news

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.core.adapter.NewsAdapter
import com.dicoding.core.data.Status
import com.dicoding.gt.MainActivity
import com.dicoding.gt.R
import com.dicoding.gt.databinding.FragmentNewsBinding
import com.dicoding.gt.detail.DetailActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {
   private var _binding: FragmentNewsBinding? = null
   // This property is only valid between onCreateView and
   // onDestroyView.
   private val binding get() = _binding!!

   private val newsViewModel: NewsViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentNewsBinding.inflate(inflater, container, false)
    return binding.root
  }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      val newsAdapter = NewsAdapter { news ->
         Intent(activity, DetailActivity::class.java).also { intent ->
            intent.putExtra(DetailActivity.EXTRA_NEWS,news)
            intent.putExtra(DetailActivity.EXTRA_KEY, news.key)
            startActivity(intent)
         }
      }

      val bottomNav: BottomNavigationView? = activity?.findViewById(R.id.nav_view)
      val menuNav = bottomNav?.menu

      when (bottomNav?.selectedItemId) {
         menuNav?.findItem(R.id.navigation_game)?.itemId -> {
            Log.d(TAG, "onViewCreated: Ini game")

            newsViewModel.gameNews.observe(viewLifecycleOwner) { news ->
               if (news != null) {
                  when (news) {
                     is Status.Loading -> showLoading(true)
                     is Status.Success -> {
                        showLoading(false)
                        newsAdapter.differ.submitList(news.data)
                     }
                     is Status.Error -> {
                        showLoading(false)
                        showError(news.message)
                     }
                  }
               }
            }
         }

         menuNav?.findItem(R.id.navigation_tech)?.itemId -> {
            Log.d(TAG, "onViewCreated: Ini tech")

            newsViewModel.techNews.observe(viewLifecycleOwner) { news ->
               if (news != null) {
                  when (news) {
                     is Status.Loading -> showLoading(true)
                     is Status.Success -> {
                        showLoading(false)
                        newsAdapter.differ.submitList(news.data)
                     }
                     is Status.Error -> {
                        showLoading(false)
                        showError(news.message)
                     }
                  }
               }
            }
         }

         menuNav?.findItem(R.id.navigation_favorite)?.itemId -> Log.d(MainActivity.TAG, "onViewCreated: Ini favorite")

         else -> {
            Log.d(TAG, "onViewCreated: Ini game")
            newsViewModel.gameNews.observe(viewLifecycleOwner) { news ->
               if (news != null) {
                  when (news) {
                     is Status.Loading -> showLoading(true)
                     is Status.Success -> {
                        showLoading(false)
                        newsAdapter.differ.submitList(news.data)
                     }
                     is Status.Error -> {
                        showLoading(false)
                        showError(news.message)
                     }
                  }
               }
            }
         }
      }

      with(binding.rvNews) {
         layoutManager = LinearLayoutManager(context)
         setHasFixedSize(true)
         adapter = newsAdapter
      }
   }

   private fun showLoading(state: Boolean) {
      binding.rvNews.visibility = if (state) View.GONE else View.VISIBLE
      binding.progressBar.visibility = if (!state) View.GONE else View.VISIBLE
      binding.viewError.root.visibility = View.GONE
   }

   private fun showError(message: String?) {
      binding.rvNews.visibility = View.GONE
      binding.viewError.root.visibility = View.VISIBLE
      binding.viewError.tvError.text = message ?: getString(R.string.something_wrong)
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }

   companion object {
      const val TAG = "NewsFragment"
   }
}