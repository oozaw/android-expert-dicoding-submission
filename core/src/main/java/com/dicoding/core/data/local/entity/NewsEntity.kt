package com.dicoding.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
   @ColumnInfo(name = "thumb")
   var thumb: String?= null,

   @ColumnInfo(name = "author")
   var author: String,

   @ColumnInfo(name = "tag")
   var tag: String? = null,

   @ColumnInfo(name = "time")
   var time: String? = null,

   @ColumnInfo(name = "title")
   var title: String,

   @PrimaryKey
   @ColumnInfo(name = "key")
   var key: String,

   @ColumnInfo(name = "desc")
   var desc: String? = null,

   @ColumnInfo(name = "figure")
   val figure: String? = null,

   @ColumnInfo(name = "categories")
   val categories: String? = null,

   @ColumnInfo(name = "content")
   val content: String? = null,

   @ColumnInfo(name = "isFavorite")
   var isFavorite: Boolean = false
)