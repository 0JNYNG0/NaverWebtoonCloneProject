package com.example.naverwebtooncloneproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.naverwebtooncloneproject.databinding.ItemViewpagerWebtoonMainTabBinding

class HomeWebToonRecyclerViewAdapter(private val mContext: Context, private val mList: MutableList<HomeWebToonData>):
    RecyclerView.Adapter<HomeWebToonRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(private val viewBinding: ItemViewpagerWebtoonMainTabBinding): RecyclerView.ViewHolder(viewBinding.root) {
        private val coverImage: ImageView = itemView.findViewById(R.id.toon_coverImage)
        private val toonTitleText: TextView = itemView.findViewById(R.id.toon_title_text)
        private val toonWriter: TextView = itemView.findViewById(R.id.toon_writer)
        private val toonGrade: TextView = itemView.findViewById(R.id.toon_grade)

        fun bind(toon: HomeWebToonData) {
            coverImage.setImageResource(toon.coverImage)
            toonTitleText.text = toon.title
            toonWriter.text = toon.writer
            toonGrade.text = toon.grade.toString()

            itemView.setOnClickListener {
                val intent = Intent(mContext, WtDetailListActivity::class.java)
                val bundle = Bundle()
                bundle.putString("coverImage", toon.coverImage.toString())
                bundle.putString("title", toon.title)
                bundle.putString("writer", toon.writer)
                bundle.putString("grade", toon.grade.toString())
                bundle.putString("imageList", toon.imageList.toString())
                intent.putExtra("data", bundle)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                mContext.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemViewpagerWebtoonMainTabBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount() = mList.size
}