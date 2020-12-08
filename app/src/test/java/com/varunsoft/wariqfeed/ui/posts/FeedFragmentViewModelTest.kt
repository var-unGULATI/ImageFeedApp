package com.varunsoft.wariqfeed.ui.posts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.varunsoft.wariqfeed.data.model.PostsItem
import com.varunsoft.wariqfeed.data.remote.response.Response
import com.varunsoft.wariqfeed.data.repository.PostsRepository
import com.varunsoft.wariqfeed.di.appModule
import com.varunsoft.wariqfeed.di.networkingModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject

@RunWith(MockitoJUnitRunner::class)
class FeedFragmentViewModelTest:KoinTest {

    @get:Rule
    public var rule: TestRule = InstantTaskExecutorRule()

    lateinit var feedFragmentViewModel: FeedFragmentViewModel
    private val testDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testDispatcher)
    val repository: PostsRepository by inject()

    @Mock
    private lateinit var totalDataObserver: Observer<ArrayList<PostsItem>>

    @Before
    fun setUp() {

        startKoin {
            modules(listOf(networkingModule(), appModule()))
        }

        feedFragmentViewModel = FeedFragmentViewModel()
        Dispatchers.setMain(testDispatcher)

        feedFragmentViewModel.totalData.observeForever(totalDataObserver)
    }

    @Test
    fun getDataSuccess_return_totalData() = testCoroutineScope.runBlockingTest {

        //arrange
        whenever(repository.getPostsData("59b3f0b0100000e30b236b7e")).thenReturn(Response())

        //act
        feedFragmentViewModel.getPostsData()

        //assert
        //assert(feedFragmentViewModel.totalData.value!!.size > 0)
        Mockito.verify(totalDataObserver).onChanged(any())
    }


    @After
    fun tearDown() {
        stopKoin()
    }
}