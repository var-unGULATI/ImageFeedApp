package com.varunsoft.wariqfeed.ui.posts

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.varunsoft.wariqfeed.data.model.PostsItem
import com.varunsoft.wariqfeed.data.repository.PostsRepository
import com.varunsoft.wariqfeed.utils.common.LoadMoreListener
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class FeedFragmentViewModel() : ViewModel(), KoinComponent {

    var loading: MutableLiveData<Boolean> = MutableLiveData(false)
    var postsData : MutableLiveData<ArrayList<PostsItem>> = MutableLiveData()
    var totalData : MutableLiveData<ArrayList<PostsItem>> = MutableLiveData(ArrayList())
    var pageCount: MutableLiveData<Int> = MutableLiveData(1)

    val repository: PostsRepository by inject()

    val loadMoreListener = object : LoadMoreListener {
        override fun loadMore() {
            getPostsData()
        }
    }

    fun getPostsData() {
        if ( pageCount.value!! < 4 && !loading.value!!) {
            var path = when (pageCount.value) {
                1 -> "59b3f0b0100000e30b236b7e"
                2 -> "59ac28a9100000ce0bf9c236"
                3 -> "59ac293b100000d60bf9c239"
                else -> "59b3f0b0100000e30b236b7e"
            }

            val errorHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
                Log.e("TAGVM", exception.localizedMessage)
            }

            //val mainJob: CompletableJob = Job()
            //var coroutineScope: CoroutineScope = CoroutineScope(mainJob + Dispatchers.Main + errorHandler)
            viewModelScope.launch(errorHandler) {
                loading.value = true
                val response = repository.getPostsData(path)
                totalData.value?.addAll(response.posts!!)
                postsData.postValue(response.posts!! as ArrayList<PostsItem>)
                loading.value = false
                pageCount.value = pageCount.value?.plus(1)
                totalData.value?.let { totalData.postValue(it) }
            }
        }
    }
}