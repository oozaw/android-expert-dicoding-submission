package com.dicoding.core.data.local

import com.dicoding.core.data.local.entity.NewsEntity
import com.dicoding.core.data.local.room.NewsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val newsDao: NewsDao) {
   fun getAllNews(): Flow<List<NewsEntity>> = newsDao.getAllNews()

   fun getGameNews(): Flow<List<NewsEntity>> = newsDao.getGameNews()

   fun getTechNews(): Flow<List<NewsEntity>> = newsDao.getTechNews()

   fun getDetailNews(key: String): Flow<NewsEntity> = newsDao.getDetailNews(key)

   fun getFavoriteNews(): Flow<List<NewsEntity>> = newsDao.getFavoriteNews()

   suspend fun insertNews(newsList: List<NewsEntity>) = newsDao.insertNews(newsList)

   suspend fun insertNews(news: NewsEntity) = newsDao.insertNews(news)

   suspend fun setFavoriteNews(news: NewsEntity, state: Boolean) {
      news.isFavorite = state
      newsDao.updateNews(news)
   }
}