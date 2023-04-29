package com.dicoding.core.utils

import com.dicoding.core.data.local.entity.NewsEntity
import com.dicoding.core.data.remote.response.NewsResponse
import com.dicoding.core.domain.model.News

object DataMapper {
   fun updateDomain(news: News, data: News): News {
      return News(
         key = news.key,
         title = news.title,
         desc = news.desc,
         thumb = news.thumb,
         time = news.time,
         author = news.author,
         tag = news.tag,
         figure = data.figure,
         categories = data.categories,
         content = data.content,
         isFavorite = news.isFavorite
      )
   }

   fun mapResponsesToEntities(input: List<NewsResponse>): List<NewsEntity> {
      val newsList = ArrayList<NewsEntity>()

      input.map {
         val news = NewsEntity(
            key = it.key!!,
            title = it.title,
            desc = it.desc,
            thumb = it.thumb,
            time = it.time,
            author = it.author,
            tag = it.tag,
            isFavorite = false
         )
         newsList.add(news)
      }
      return newsList
   }

    fun mapResponsesToEntities(input: NewsResponse, key: String): NewsEntity {
       val figure: String = input.figure.toString().replace("[","")
          .replace("]", "").replace(", ", ",")
       val categories: String = input.categories.toString().replace("[","")
          .replace("]", "").replace(", ", ",")
       val content: String = input.content.toString().replace("[","")
          .replace("]", "").replace(", ", ",")

       return NewsEntity(
          author = input.author,
          title = input.title,
          figure = figure,
          categories = categories,
          content = content,
          key = key
       )
    }

   fun mapEntitiesToDomain(input: List<NewsEntity>): List<News> =
      input.map {
         News(
            key = it.key,
            title = it.title,
            desc = it.desc,
            thumb = it.thumb,
            time = it.time,
            author = it.author,
            tag = it.tag,
            isFavorite = it.isFavorite
         )
      }

   fun mapEntitiesToDomain(input: NewsEntity): News {
      val figure: List<String>? = input.figure?.split(",")?.toList()
      val categories: List<String>? = input.categories?.split(",")?.toList()
      val content: List<String>? = input.content?.split(",")?.toList()

      return News(
         key = input.key,
         title = input.title,
         desc = input.desc,
         thumb = input.thumb,
         time = input.time,
         author = input.author,
         tag = input.tag,
         figure = figure,
         categories = categories,
         content = content,
         isFavorite = input.isFavorite
      )
   }

   fun mapDomainToEntity(input: News): NewsEntity {
      val figure: String = input.figure.toString().replace("[","")
         .replace("]", "").replace(", ", ",")
      val categories: String = input.categories.toString().replace("[","")
         .replace("]", "").replace(", ", ",")
      val content: String = input.content.toString().replace("[","")
         .replace("]", "").replace(", ", ",")

      return NewsEntity(
         key = input.key!!,
         title = input.title,
         desc = input.desc,
         thumb = input.thumb,
         time = input.time,
         author = input.author,
         tag = input.tag,
         figure = figure,
         categories = categories,
         content = content,
         isFavorite = input.isFavorite
      )
   }
}