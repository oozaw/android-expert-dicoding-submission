package com.dicoding.gt.di

import com.dicoding.core.domain.usecase.NewsUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
   fun newsUseCase(): NewsUseCase
}