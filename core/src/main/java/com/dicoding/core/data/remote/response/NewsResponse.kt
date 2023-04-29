package com.dicoding.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class NewsResponse(

	@field:SerializedName("thumb")
	val thumb: String?= null,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("tag")
	val tag: String? = null,

	@field:SerializedName("time")
	val time: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("key")
	val key: String? = null,

	@field:SerializedName("desc")
	val desc: String? = null,

	@field:SerializedName("figure")
	val figure: List<String>? = null,

	@field:SerializedName("categories")
	val categories: List<String>? = null,

	@field:SerializedName("content")
	val content: List<String>? = null
)
