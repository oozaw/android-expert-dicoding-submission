package com.dicoding.gt.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.core.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(newsUseCase: NewsUseCase) : ViewModel() {

    val gameNews = newsUseCase.getGameNews().asLiveData()
    val techNews = newsUseCase.getTechNews().asLiveData()
}