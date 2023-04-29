package com.dicoding.gt.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.core.domain.usecase.NewsUseCase

class FavoriteViewModel(newsUseCase: NewsUseCase): ViewModel() {
   val favNews = newsUseCase.getFavoriteNews().asLiveData()
}