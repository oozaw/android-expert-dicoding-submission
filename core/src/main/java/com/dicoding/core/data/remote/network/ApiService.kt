package com.dicoding.core.data.remote.network

import com.dicoding.core.data.remote.response.NewsDetailResponse
import com.dicoding.core.data.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

   @GET("api/games")
   suspend fun getGameNews(): List<NewsResponse>

   @GET("api/tech")
   suspend fun getTechNews(): List<NewsResponse>

   @GET("api/detail/{key}")
   suspend fun getDetailNews(
      @Path(value = "key", encoded = true) key: String
   ): NewsDetailResponse
}