package com.dicoding.core.data.remote

import android.util.Log
import com.dicoding.core.data.remote.network.ApiService
import com.dicoding.core.data.remote.response.ApiStatus
import com.dicoding.core.data.remote.response.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

   suspend fun getGameNews(): Flow<ApiStatus<List<NewsResponse>>> {
      return flow {
         try {
            val response = apiService.getGameNews()

            if (response.isNotEmpty()){
               emit(ApiStatus.Success(response))
            } else {
               emit(ApiStatus.Empty)
            }
         } catch (e: Exception) {
            emit(ApiStatus.Error(e.toString()))
            Log.e(TAG, "getGameNews: $e")
         }
      }.flowOn(Dispatchers.IO)
   }

   suspend fun getTechNews(): Flow<ApiStatus<List<NewsResponse>>> {
      return flow {
         try {
            val response = apiService.getTechNews()

            if (response.isNotEmpty()){
               emit(ApiStatus.Success(response))
            } else {
               emit(ApiStatus.Empty)
            }
         } catch (e: Exception) {
            emit(ApiStatus.Error(e.toString()))
            Log.e(TAG, "getGameNews: $e")
         }
      }.flowOn(Dispatchers.IO)
   }

   suspend fun getDetailNews(key: String): Flow<ApiStatus<NewsResponse>> = flow {
      try {
         val response = apiService.getDetailNews(key)

         if (response.status) {
            emit(ApiStatus.Success(response.results))
         } else {
            emit(ApiStatus.Empty)
         }
      } catch (e: Exception) {
         emit(ApiStatus.Error(e.toString()))
         Log.e(TAG, "getDetailNews: $e")
      }
   }.flowOn(Dispatchers.IO)

   companion object {
      const val TAG = "RemoteDataSource"
   }
}