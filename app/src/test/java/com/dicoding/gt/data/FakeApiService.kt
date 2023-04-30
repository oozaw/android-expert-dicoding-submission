package com.dicoding.gt.data

import com.dicoding.core.data.remote.network.ApiService
import com.dicoding.core.data.remote.response.NewsDetailResponse
import com.dicoding.core.data.remote.response.NewsResponse
import com.dicoding.gt.utils.DataDummy

class FakeApiService: ApiService {
   override suspend fun getGameNews(): List<NewsResponse> {
      return listOf(DataDummy.generateDummyNewsResponse())
   }

   override suspend fun getTechNews(): List<NewsResponse> {
      return listOf(DataDummy.generateDummyNewsResponse())
   }

   override suspend fun getDetailNews(key: String): NewsDetailResponse {
      return DataDummy.generateDummyDetailNewsResponse()
   }
}