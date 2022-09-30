package com.example.naverwebtooncloneproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WtDetailListRecyclerViewAdapter(private val mContext: Context, private val mList: MutableList<HomeWebToonData>):
    RecyclerView.Adapter<WtDetailListRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val detailItemImage: ImageView = itemView.findViewById(R.id.webtoon_detail_list_item_image)
        private val detailItemText: TextView = itemView.findViewById(R.id.webtoon_detail_list_item_title)
        private val detailItemGrade: TextView = itemView.findViewById(R.id.webtoon_detail_list_item_grade)

        fun bind(toon: HomeWebToonData) {
            detailItemImage.setImageResource(toon.coverImage)
            detailItemText.text = toon.title
            detailItemGrade.text = toon.grade.toString()

            itemView.setOnClickListener {
                val intent = Intent(mContext, WebToonWatchActivity::class.java)
                val bundle = Bundle()
                //bundle.putString("coverImage", toon.coverImage.toString())
                //bundle.putString("title", toon.title)
                //bundle.putString("writer", toon.writer)
                //bundle.putString("grade", toon.grade.toString())
                bundle.putString("imageList", toon.imageList.toString())
                intent.putExtra("data", bundle)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                mContext.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.webtoon_detail_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = mList[position]
        holder.bind(user)
    }

    override fun getItemCount() = mList.size
}