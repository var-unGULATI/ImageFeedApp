package com.varunsoft.wariqfeed.data.remote

import com.varunsoft.wariqfeed.data.remote.response.Response
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService{

    @GET(EndPoints.GET_POSTS)
    suspend fun getPosts(@Path("id")id:String): Response
}