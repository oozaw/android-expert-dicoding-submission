package com.dicoding.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News (

   val thumb: String?= null,

   val author: String,

   val tag: String? = null,

   val time: String? = null,

   val title: String,

   val key: String? = null,

   val desc: String? = null,

   val figure: List<String>? = null,

   val categories: List<String>? = null,

   val content: List<String>? = null,

   val isFavorite: Boolean
) : Parcelable