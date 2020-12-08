package com.varunsoft.wariqfeed.data.model

import com.google.gson.annotations.SerializedName

data class PostsItem(

        @field:SerializedName("shares")
        val shares: Int? = null,

        @field:SerializedName("event_date")
        val eventDate: Int? = null,

        @field:SerializedName("event_name")
        val eventName: String? = null,

        @field:SerializedName("id")
        val id: String? = null,

        @field:SerializedName("views")
        val views: Int? = null,

        @field:SerializedName("thumbnail_image")
        val thumbnailImage: String? = null,

        @field:SerializedName("likes")
        val likes: Int? = null
)