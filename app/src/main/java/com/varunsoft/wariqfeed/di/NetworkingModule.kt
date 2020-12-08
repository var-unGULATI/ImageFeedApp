package com.varunsoft.wariqfeed.di

import com.varunsoft.wariqfeed.BuildConfig
import com.varunsoft.wariqfeed.data.remote.APIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun networkingModule() = module {

    var baseUrl = "https://www.mocky.io/"
    val NETWORK_CALL_TIMEOUT = 60

    single(named("BASE_URL")) {
        baseUrl
    }

    single {
        HttpLoggingInterceptor()
                .apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                }
    }

    single {
        OkHttpClient.Builder()
                .addInterceptor(get<HttpLoggingInterceptor>())
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    single {
        Retrofit.Builder()
                .client(get())
                .baseUrl(get<String>(named("BASE_URL")))
                .addConverterFactory(GsonConverterFactory.create())
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    single{
        get<Retrofit>().create(APIService::class.java)
    }
}