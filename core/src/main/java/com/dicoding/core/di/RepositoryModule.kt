package com.dicoding.core.di

import com.dicoding.core.data.NewsRepository
import com.dicoding.core.domain.repository.INewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

   @Binds
   abstract fun provideRepository(tourismRepository: NewsRepository): INewsRepository
}