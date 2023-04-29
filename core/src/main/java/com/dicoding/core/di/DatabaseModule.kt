package com.dicoding.core.di

import android.content.Context
import androidx.room.Room
import com.dicoding.core.BuildConfig.PASSPHRASE
import com.dicoding.core.data.local.room.NewsDao
import com.dicoding.core.data.local.room.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

   private val passphrase: ByteArray = SQLiteDatabase.getBytes(PASSPHRASE.toCharArray())
   val factory = SupportFactory(passphrase)
   @Singleton
   @Provides
   fun provideDatabase(@ApplicationContext context: Context): NewsDatabase = Room.databaseBuilder(
      context, NewsDatabase::class.java, "News.db"
   ).fallbackToDestructiveMigration()
      .openHelperFactory(factory)
      .build()

   @Provides
   fun provideNewsDao(database: NewsDatabase): NewsDao = database.newsDao()
}