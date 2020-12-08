package com.varunsoft.wariqfeed.ui.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.varunsoft.wariqfeed.R
import com.varunsoft.wariqfeed.databinding.FragmentFeedBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class FeedFragment : Fragment() {

    lateinit var binding: FragmentFeedBinding
    lateinit var postsAdapter: PostsAdapter
    var loading = false
    var pageCount by Delegates.notNull<Int>()

    //val feedFragmentViewModel: FeedFragmentViewModel by lazy { ViewModelProvider(this).get(FeedFragmentViewModel::class.java) }
    val feedFragmentViewModel: FeedFragmentViewModel by viewModel<FeedFragmentViewModel>()

    companion  object{
        const val TAG = "Feed Fragment"
        fun newInstance(): FeedFragment {
            val args = Bundle()
            val fragment = FeedFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpObservers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        postsAdapter = PostsAdapter(context)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false)
        binding.setLifecycleOwner(this)
        binding.viewModel = feedFragmentViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = postsAdapter
        }
        if(!(savedInstanceState?.getBoolean("initiated")?:false))
            getPostsData()
    }

    private fun getPostsData() {
        feedFragmentViewModel.getPostsData()
    }

    private fun setUpObservers(){
        feedFragmentViewModel.postsData.observe(this){
            if(postsAdapter.posts.size != 0)
                postsAdapter.addData(it)
        }

        feedFragmentViewModel.totalData.observe(this){
            if(postsAdapter.posts.size == 0)
                postsAdapter.addData(it)
        }

        feedFragmentViewModel.loading.observe(this){
            loading = it
        }

        feedFragmentViewModel.pageCount.observe(this){
            pageCount = it
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("initiated",true)
    }

}