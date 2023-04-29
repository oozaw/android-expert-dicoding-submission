package com.dicoding.gt

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.dicoding.gt.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

   private var _binding: ActivityMainBinding? = null
   private val binding get() = _binding!!

   private var selectedNavId: Int = 0

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      _binding = ActivityMainBinding.inflate(layoutInflater)
      setContentView(binding.root)

      val bottomNav = binding.navView
      selectedNavId = bottomNav.selectedItemId
      @Suppress("DEPRECATION")
      bottomNav.setOnNavigationItemSelectedListener(this)
   }

   override fun onResume() {
      super.onResume()
      binding.navView.selectedItemId = selectedNavId
   }

   override fun onDestroy() {
      super.onDestroy()
      _binding = null
   }

   override fun onNavigationItemSelected(item: MenuItem): Boolean {
      var title = getString(R.string.app_name)
      when (item.itemId) {
         R.id.navigation_game -> {
            findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.navigation_game)
            selectedNavId = R.id.navigation_game
            title = getString(R.string.title_game)
         }
         R.id.navigation_tech -> {
            findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.navigation_tech)
            selectedNavId = R.id.navigation_tech
            title = getString(R.string.title_tech)
         }
         R.id.navigation_favorite -> {
            val uri = Uri.parse("gt://favorite")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
            selectedNavId = R.id.navigation_game
         }
      }
      supportActionBar?.title = title

      return true
   }

   companion object {
      const val TAG = "MainActivity"
   }
}