package com.example.naverwebtooncloneproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeWebToonRecyclerViewAdapter(private val mContext: Context, private val mList: MutableList<HomeWebToonData>):
    RecyclerView.Adapter<HomeWebToonRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val coverImage: ImageView = itemView.findViewById(R.id.toon_coverImage)
        private val toonTitleText: TextView = itemView.findViewById(R.id.toon_title_text)
        private val toonWriter: TextView = itemView.findViewById(R.id.toon_writer)
        private val toonGrade: TextView = itemView.findViewById(R.id.toon_grade)

        fun bind(toon: HomeWebToonData) {
            coverImage.setImageResource(toon.coverImage)
            toonTitleText.text = toon.title
            toonWriter.text = toon.writer
            toonGrade.text = toon.grade.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.main_home_tab_viewpager_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = mList[position]
        holder.bind(user)
    }

    override fun getItemCount() = mList.size
}