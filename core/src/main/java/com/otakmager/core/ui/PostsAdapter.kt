package com.otakmager.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.otakmager.core.R
import com.otakmager.core.databinding.ItemListPostsBinding
import com.otakmager.core.domain.model.Posts

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.ListViewHolder>() {

    private var listData = ArrayList<Posts>()
    var onItemClick: ((Posts) -> Unit)? = null

    fun setData(newListData: List<Posts>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_posts, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListPostsBinding.bind(itemView)
        fun bind(data: Posts) {
            with(binding) {
                tvItemTitle.text = data.title
                tvItemBody.text = data.body
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}