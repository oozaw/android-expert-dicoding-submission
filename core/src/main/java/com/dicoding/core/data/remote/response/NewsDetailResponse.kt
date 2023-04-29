package com.dicoding.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class NewsDetailResponse(

	@field:SerializedName("method")
	val method: String,

	@field:SerializedName("status")
	val status: Boolean,

	@field:SerializedName("results")
	val results: NewsResponse,

	@field:SerializedName("message")
	val message: String
)

data class Results(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("figure")
	val figure: List<String>,

	@field:SerializedName("thumb")
	val thumb: String,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("categories")
	val categories: List<String>,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("content")
	val content: List<String>
)
