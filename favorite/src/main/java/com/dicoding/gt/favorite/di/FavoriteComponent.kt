package com.dicoding.gt.favorite.di

import android.content.Context
import com.dicoding.gt.di.FavoriteModuleDependencies
import com.dicoding.gt.favorite.ui.FavoriteActivity
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {

   fun inject(favoriteActivity: FavoriteActivity)

   @Component.Builder
   interface Builder {
      fun context(@BindsInstance context: Context): Builder
      fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
      fun build(): FavoriteComponent
   }
}