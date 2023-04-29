package com.dicoding.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsDetail (

   val date: String,

   val figure: List<String>,

   val thumb: String,

   val author: String,

   val categories: List<String>,

   val title: String,

   val content: List<String>
) : Parcelable