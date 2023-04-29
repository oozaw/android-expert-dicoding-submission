package com.dicoding.core.data.local.room

import androidx.room.*
import com.dicoding.core.data.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

   @Query("SELECT * FROM news")
   fun getAllNews(): Flow<List<NewsEntity>>

   @Query("SELECT * FROM news where tag = 'Games' ORDER BY key DESC")
   fun getGameNews(): Flow<List<NewsEntity>>

   @Query("SELECT * FROM news where tag = 'Tech' ORDER BY key DESC")
   fun getTechNews(): Flow<List<NewsEntity>>

   @Query("SELECT * FROM news where key = :key")
   fun getDetailNews(key: String): Flow<NewsEntity>

   @Query("SELECT * FROM news where isFavorite = 1 ORDER BY key DESC")
   fun getFavoriteNews(): Flow<List<NewsEntity>>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertNews(news: List<NewsEntity>)

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertNews(news: NewsEntity)

   @Update
   suspend fun updateNews(news: NewsEntity)
}