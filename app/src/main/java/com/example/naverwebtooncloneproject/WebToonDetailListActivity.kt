package com.example.naverwebtooncloneproject

import android.content.ClipData
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.naverwebtooncloneproject.databinding.ActivityMainBinding
import com.example.naverwebtooncloneproject.databinding.ActivityWebtoonDetailListBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class WebToonDetailListActivity : AppCompatActivity(){
    private lateinit var binding: ActivityWebtoonDetailListBinding
    private lateinit var toonData: Bundle
    private lateinit var adapter: WtDetailListRecyclerViewAdapter
    private var toonList = mutableListOf<HomeWebToonData>()

    private var detailTopImage = 0
    private var detailTitle = ""
    private var detailWriter = ""
    private var detailGrade = 0f
    private var imageList = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebtoonDetailListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        toonData = intent.getBundleExtra("data")!!

        detailTopImage = toonData.getString("coverImage", "0").toInt()
        detailTitle = toonData.getString("title", "noTitle")
        detailWriter = toonData.getString("writer", "noWriter")
        detailGrade = toonData.getString("grade", "0.00").toFloat()
        imageList = toonData.getString("imageList", "0").toInt()

        binding.webtoonDetailTopImage.setImageResource(detailTopImage)
        binding.webtoonDetailTitle.text = detailTitle
        binding.webtoonDetailWriter.text = detailWriter
        binding.webtoonDetailGrade.text = detailGrade.toString()

        for (i in 30 downTo 1){
            val wData = HomeWebToonData(detailTopImage, "$i 화 ($detailTitle)", detailWriter, detailGrade.toFloat(), imageList)
            toonList.add(wData)
        }



    }

    override fun onResume() {
        super.onResume()

        adapter = WtDetailListRecyclerViewAdapter(this, toonList)
        binding.webtoonDetailRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.webtoonDetailRecyclerView.adapter = adapter
    }

    override fun onBackPressed() {
        finish()
    }

}