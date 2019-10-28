package com.mdlicht.zb.simplemvpexample

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GitViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val title = itemView.findViewById<TextView>(R.id.title)
    val star = itemView.findViewById<TextView>(R.id.star)

    class Params {
        var title: String? = null
        var url: String? = null
        var star: Int = 0
    }

    fun onBind(position: Int, item: Params) {
        title.text = item.title
        star.text = item.star.toString()
        itemView.setOnClickListener {
            it.context.startActivity(Intent(ACTION_VIEW, Uri.parse(item.url)).apply {
                putExtra("test", 100)
                putExtra("test22", "sample")
            })
        }
    }
}