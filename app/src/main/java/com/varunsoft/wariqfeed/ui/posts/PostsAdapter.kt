package com.varunsoft.wariqfeed.ui.posts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.varunsoft.wariqfeed.R
import com.varunsoft.wariqfeed.data.model.PostsItem


class PostsAdapter(var context: Context?) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    var posts  = ArrayList<PostsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
        return PostsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.likes.text = "Likes: ${posts.get(position).likes}"
        holder.views.text = (position + 1).toString()
        var url = posts.get(position).thumbnailImage
        url = url!!.replace(":","s:")
        Glide.with(context!!).load(url).into(holder.image)



    }

    fun addData(posts: List<PostsItem>){
        val oldCount = itemCount
        this.posts.addAll(posts)
        val currentCount = itemCount

        if(oldCount == 0 && currentCount > 0){
            notifyDataSetChanged()
        }
        else if(oldCount in 1 until currentCount){
            notifyItemRangeChanged(oldCount - 1, currentCount - oldCount)
        }
    }

    class PostsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var image: ImageView = itemView.findViewById(R.id.image)
        var likes: TextView = itemView.findViewById(R.id.likes)
        var views: TextView = itemView.findViewById(R.id.views)


    }
}