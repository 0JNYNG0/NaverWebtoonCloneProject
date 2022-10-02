package com.example.naverwebtooncloneproject

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.naverwebtooncloneproject.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var toonList = mutableListOf<MutableList<HomeWebToonData>>()
    private lateinit var mReceiver: BroadcastReceiver
    private lateinit var mainViewpager: ViewPager2

    private var backWaitTime = 0L
    private var imageIdData: Int = 0
    private var titleData: String = ""
    private var writerData: String = ""
    private var gradeData: Float = 0f

    private var initComplete = false
    var currentDay = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mReceiver = MyReceiver()
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        val batteryStatus = registerReceiver(mReceiver, intentFilter)
        Log.d("receiver", batteryStatus.toString())



        // 웹툰들의 정보가 담긴 toonList를 아래 MainHomeFragment 호출할 때 정보를 보내준 뒤
        // recyclerView 어댑터를 연결시켜준다
        for (i in 0 .. 10) { // 0_index (new) / 1_index (Mon) / 2_index (Tue) / ... / 9_index (fin)
            val el = mutableListOf<HomeWebToonData>()

            val idx: Int = when (i) {
                0 -> 2
                in 1..7 -> i
                8 -> 3
                else -> 4
            }
            for (j in 0 .. 29) {
                val name = "wt$idx" + "_" + "$j"

                val imageId =
                    resources.getIdentifier(name, "drawable", "com.example.naverwebtooncloneproject")
                el.add(HomeWebToonData(imageId, "Title_$i", "Writer_$i", 0.00f, 70))
            }

            toonList.add(el)
        }


        //for (i in 1 until 84) {
        //    val name = "sq_0"
        //    val imageId =
        //        resources.getIdentifier(name, "drawable", "com.example.naverwebtooncloneproject")
        //    toonList.add(
        //        HomeWebToonData(
        //            imageId, "Title_$i", "Writer_$i", i.toFloat(), 70
        //       )
        //    )
        //}

        binding.homeBottomNavigationView.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home_item -> {
                        changeFragment(BN_FG_Home_Fragment(this@MainActivity, toonList))
                    }
                    R.id.recommend_item -> {
                        changeFragment(BN_FG_Recommend_Fragment())
                    }
                    R.id.bestChallenge_item -> {
                        changeFragment(BN_FG_BestChallenge_Fragment())
                    }
                    R.id.mine_item -> {
                        changeFragment(BN_FG_Mine_Fragment())
                    }
                    R.id.more_item -> {
                        changeFragment(BN_FG_More_Fragment())
                    }
                }
                true
            }
            selectedItemId = R.id.home_item
        }

    }

    override fun onStart() {
        super.onStart()
        mainViewpager = findViewById(R.id.home_main_grid_viewpager)

        val cal: Calendar = Calendar.getInstance() // 달력 객체
        val week = cal.get(Calendar.DAY_OF_WEEK) // 요일 받아오기
        Log.d("week", week.toString())

        if (!initComplete) { // 앱 첫 실행이라면 해당 요일 기준으로 탭 변경
            if (week == 1) {
                mainViewpager.currentItem = 7
            } else {
                mainViewpager.currentItem = week - 1
            }
            initComplete = true
        }

    }

    override fun onResume() {
        super.onResume()

        mReceiver = MyReceiver()
        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_SCREEN_ON)
        }
        registerReceiver(mReceiver, filter)
    }

    override fun onPause() {
        currentDay = mainViewpager.currentItem // 보고있었던 웹툰 탭 위치 저장
        super.onPause()
        unregisterReceiver(mReceiver)


    }

    override fun onStop() {
        super.onStop()

    }


    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(binding.homeFrameView.id, fragment).commit()
    }
    private fun backStackFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().addToBackStack(null).replace(binding.homeFrameView.id, fragment).commit()
    }

    override fun onBackPressed() {
        if(binding.homeBottomNavigationView.selectedItemId == R.id.home_item
            && System.currentTimeMillis() - backWaitTime >= 1500) {
            backWaitTime = System.currentTimeMillis()
            Toast.makeText(this, "'뒤로'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        else if (binding.homeBottomNavigationView.selectedItemId != R.id.home_item) {
            binding.homeBottomNavigationView.run { selectedItemId = R.id.home_item }
        }
        else {
            finish()
        }


    }


}