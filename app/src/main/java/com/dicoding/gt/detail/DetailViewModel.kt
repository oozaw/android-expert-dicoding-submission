package com.dicoding.gt.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.core.domain.model.News
import com.dicoding.core.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val newsUseCase: NewsUseCase): ViewModel() {

   fun detailNews(oldData: News, key: String) = newsUseCase.getDetailNews(oldData, key).asLiveData()

   fun setFavoriteNews(news: News, isFav: Boolean) = viewModelScope.launch(Dispatchers.IO) {
      newsUseCase.setFavoriteNews(news, isFav)
   }
}