package com.mdlicht.zb.simplemvpexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GitRecyclerAdapter: RecyclerView.Adapter<GitViewHolder>() {

    private val items = mutableListOf<GitData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item, parent, false)
        return GitViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GitViewHolder, position: Int) {
        holder.onBind(position, GitViewHolder.Params().apply {
            title = items[position].title
            star = items[position].star
            url = items[position].url
        })
    }

    fun setItems(items: List<GitData>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}