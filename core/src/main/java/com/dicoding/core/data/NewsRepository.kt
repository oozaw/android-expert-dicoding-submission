package com.dicoding.core.data

import com.dicoding.core.data.local.LocalDataSource
import com.dicoding.core.data.remote.RemoteDataSource
import com.dicoding.core.data.remote.response.ApiStatus
import com.dicoding.core.data.remote.response.NewsResponse
import com.dicoding.core.domain.model.News
import com.dicoding.core.domain.repository.INewsRepository
import com.dicoding.core.utils.AppExecutors
import com.dicoding.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
   private val remoteDataSource: RemoteDataSource,
   private val localDataSource: LocalDataSource,
   private val appExecutors: AppExecutors
): INewsRepository {
   override fun getGameNews(): Flow<Status<List<News>>> =
      object : NetworkBoundResource<List<News>,
         List<NewsResponse>>() {
         override fun loadFromDB(): Flow<List<News>> =
            localDataSource.getGameNews().map {
               DataMapper.mapEntitiesToDomain(it)
            }

         override suspend fun createCall(): Flow<ApiStatus<List<NewsResponse>>> = remoteDataSource.getGameNews()

         override suspend fun saveCallResult(data: List<NewsResponse>) {
            val newsList = DataMapper.mapResponsesToEntities(data)
            localDataSource.insertNews(newsList)
         }

         override fun shouldFetch(data: List<News>?): Boolean = data == null || data.isEmpty()
      }.asFlow()

   override fun getTechNews(): Flow<Status<List<News>>> =
      object : NetworkBoundResource<List<News>,
              List<NewsResponse>>() {
         override fun loadFromDB(): Flow<List<News>> =
            localDataSource.getTechNews().map {
               DataMapper.mapEntitiesToDomain(it)
            }

         override suspend fun createCall(): Flow<ApiStatus<List<NewsResponse>>> = remoteDataSource.getTechNews()

         override suspend fun saveCallResult(data: List<NewsResponse>) {
            val newsList = DataMapper.mapResponsesToEntities(data)
            localDataSource.insertNews(newsList)
         }

         override fun shouldFetch(data: List<News>?): Boolean = data == null || data.isEmpty()
      }.asFlow()

   override fun getFavoriteNews(): Flow<List<News>> = localDataSource.getFavoriteNews().map {
      DataMapper.mapEntitiesToDomain(it)
   }

   override suspend fun setFavoriteNews(news: News, state: Boolean) {
      val newsEntity = DataMapper.mapDomainToEntity(news)
      localDataSource.setFavoriteNews(newsEntity, state)
   }

   override fun getDetailNews(oldData: News, key: String): Flow<Status<News>> =
      object : NetworkBoundResource<News, NewsResponse>() {
         override fun loadFromDB(): Flow<News> =
            localDataSource.getDetailNews(key).map { DataMapper.mapEntitiesToDomain(it) }

         override suspend fun createCall(): Flow<ApiStatus<NewsResponse>> = remoteDataSource.getDetailNews(key)

         override suspend fun saveCallResult(data: NewsResponse) {
            val news = DataMapper.mapResponsesToEntities(data, key)
            news.tag = oldData.tag
            news.time = oldData.time
            news.thumb = oldData.thumb
            news.desc = oldData.desc
            news.isFavorite = oldData.isFavorite
            localDataSource.insertNews(news)
         }

         override fun shouldFetch(data: News?): Boolean =
            data?.figure == null || data.content == null || data.categories == null

      }.asFlow()

   companion object {
      const val TAG = "NewsRepository"
   }
}