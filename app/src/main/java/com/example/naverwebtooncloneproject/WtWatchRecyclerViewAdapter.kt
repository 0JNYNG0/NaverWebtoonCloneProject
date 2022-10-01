package com.example.naverwebtooncloneproject

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.naverwebtooncloneproject.databinding.ItemWebtoonWatchBinding

class WtWatchRecyclerViewAdapter(private val mContext: Context, private val mList: MutableList<WebtoonWatchData>):
    RecyclerView.Adapter<WtWatchRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(private val viewBinding: ItemWebtoonWatchBinding): RecyclerView.ViewHolder(viewBinding.root) {
        private val watchCutSceneNumber = viewBinding.webtoonWatchCutSceneNumber

        fun bind(toon: WebtoonWatchData) {
            watchCutSceneNumber.text = toon.imageList.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemWebtoonWatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount() = mList.size
}