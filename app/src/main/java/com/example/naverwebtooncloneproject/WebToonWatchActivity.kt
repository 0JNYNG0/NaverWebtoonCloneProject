package com.example.naverwebtooncloneproject

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.naverwebtooncloneproject.databinding.ActivityWebtoonWatchBinding

class WebToonWatchActivity : AppCompatActivity(){
    private lateinit var binding: ActivityWebtoonWatchBinding
    private lateinit var toonData: Bundle
    private lateinit var adapter: WtWatchRecyclerViewAdapter
    private var toonList = mutableListOf<WatchToonData>()

    private var watchToonImage = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebtoonWatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @RequiresApi(Build.VERSION_CODES.M)
        if (!NetworkManager.checkNetworkState(this)) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("안내")
                .setMessage("네트워크 문제로 연결되지 않았습니다.\n다시 시도해주세요.")
                .setPositiveButton("확인"
                ) { _, _ ->
                    finish()
                }
            builder.show()
        }

        toonData = intent.getBundleExtra("data")!!

        watchToonImage = toonData.getString("imageList", "0").toInt()

        for (i in 1 until watchToonImage+1){
            val wData = WatchToonData(i)
            toonList.add(wData)

        }

    }

    override fun onResume() {
        super.onResume()

        adapter = WtWatchRecyclerViewAdapter(this, toonList)
        binding.webtoonWatchRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.webtoonWatchRecyclerView.adapter = adapter
    }

    override fun onBackPressed() {
        finish()
    }
}