package com.varunsoft.wariqfeed.di

import com.varunsoft.wariqfeed.data.repository.PostsRepository
import com.varunsoft.wariqfeed.ui.posts.FeedFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun appModule() = module {

    single { PostsRepository() }

    viewModel { FeedFragmentViewModel() }
}