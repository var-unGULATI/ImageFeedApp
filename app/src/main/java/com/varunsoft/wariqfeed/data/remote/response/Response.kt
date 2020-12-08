package com.varunsoft.wariqfeed.data.remote.response

import com.google.gson.annotations.SerializedName
import com.varunsoft.wariqfeed.data.model.PostsItem

data class Response(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("posts")
	val posts: List<PostsItem>? = null
)


