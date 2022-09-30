package com.example.naverwebtooncloneproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WtWatchRecyclerViewAdapter(private val mContext: Context, private val mList: MutableList<WatchToonData>):
    RecyclerView.Adapter<WtWatchRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val watchCutSceneNumber: TextView = itemView.findViewById(R.id.webtoon_watch_cutScene_number)

        fun bind(toon: WatchToonData) {
            watchCutSceneNumber.text = toon.imageList.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.webtoon_watch_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = mList[position]
        holder.bind(user)
    }

    override fun getItemCount() = mList.size
}