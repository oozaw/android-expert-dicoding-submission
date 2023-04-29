package com.dicoding.core.di

import com.dicoding.core.BuildConfig.BASE_URL
import com.dicoding.core.BuildConfig.HOSTNAME
import com.dicoding.core.BuildConfig.SHA256_1
import com.dicoding.core.BuildConfig.SHA256_2
import com.dicoding.core.BuildConfig.SHA256_3
import com.dicoding.core.data.remote.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

   private val certificatePinner = CertificatePinner.Builder()
      .add(HOSTNAME, SHA256_1)
      .add(HOSTNAME, SHA256_2)
      .add(HOSTNAME, SHA256_3)
      .build()
   @Provides
   fun provideOkHttpClient(): OkHttpClient {
      return OkHttpClient.Builder()
         .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
         .connectTimeout(120, TimeUnit.SECONDS)
         .readTimeout(120, TimeUnit.SECONDS)
         .certificatePinner(certificatePinner)
         .build()
   }

   @Provides
   fun provideApiService(client: OkHttpClient): ApiService {
      val retrofit = Retrofit.Builder()
         .baseUrl(BASE_URL)
         .addConverterFactory(GsonConverterFactory.create())
         .client(client)
         .build()
      return retrofit.create(ApiService::class.java)
   }
}