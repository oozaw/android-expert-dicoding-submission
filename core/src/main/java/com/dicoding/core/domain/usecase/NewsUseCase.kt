package com.dicoding.core.domain.usecase

import com.dicoding.core.data.Status
import com.dicoding.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {

   fun getGameNews(): Flow<Status<List<News>>>

   fun getTechNews(): Flow<Status<List<News>>>

   fun getFavoriteNews(): Flow<List<News>>

   suspend fun setFavoriteNews(news: News, state: Boolean)

   fun getDetailNews(oldData: News, key: String): Flow<Status<News>>
}