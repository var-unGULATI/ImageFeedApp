package com.varunsoft.wariqfeed.data.repository

import com.varunsoft.wariqfeed.data.remote.APIService
import com.varunsoft.wariqfeed.data.remote.response.Response
import org.koin.core.KoinComponent
import org.koin.core.inject

class PostsRepository: KoinComponent {

    suspend fun getPostsData(path:String) : Response {

        val apiService: APIService by inject()
        return apiService.getPosts(path)
    }
}