package com.dicoding.core.domain.usecase

import com.dicoding.core.data.Status
import com.dicoding.core.domain.model.News
import com.dicoding.core.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsInteractor @Inject constructor(
   private val newsRepository: INewsRepository
): NewsUseCase {

   override fun getGameNews(): Flow<Status<List<News>>> = newsRepository.getGameNews()

   override fun getTechNews(): Flow<Status<List<News>>> = newsRepository.getTechNews()

   override fun getFavoriteNews(): Flow<List<News>> = newsRepository.getFavoriteNews()

   override suspend fun setFavoriteNews(news: News, state: Boolean) = newsRepository.setFavoriteNews(news, state)

   override fun getDetailNews(oldData: News, key: String): Flow<Status<News>> = newsRepository.getDetailNews(oldData, key)
}