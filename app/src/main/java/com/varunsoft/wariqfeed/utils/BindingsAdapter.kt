package com.varunsoft.wariqfeed.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.varunsoft.wariqfeed.utils.common.LoadMoreListener

@BindingAdapter("app:onLoadMore")
fun onLoadMore(view:RecyclerView, listener: LoadMoreListener){
    view.addOnScrollListener(object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            view.layoutManager?.run {
                if (this is LinearLayoutManager
                        && itemCount > 0
                        && itemCount == findLastVisibleItemPosition() + 1)
                    listener.loadMore()
            }
        }
    })

}