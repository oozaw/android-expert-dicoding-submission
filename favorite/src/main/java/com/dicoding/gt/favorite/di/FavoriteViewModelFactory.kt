package com.dicoding.gt.favorite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.core.domain.usecase.NewsUseCase
import com.dicoding.gt.favorite.ui.FavoriteViewModel
import javax.inject.Inject

class FavoriteViewModelFactory @Inject constructor(
   private val newsUseCase: NewsUseCase
) : ViewModelProvider.NewInstanceFactory() {

   @Suppress("UNCHECKED_CAST")
   override fun <T : ViewModel> create(modelClass: Class<T>): T =
      when {
         modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
            FavoriteViewModel(newsUseCase) as T
         }
         else -> throw Throwable("Unknown Viewmodel class: " + modelClass.name)
      }
}