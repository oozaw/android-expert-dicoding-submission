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
