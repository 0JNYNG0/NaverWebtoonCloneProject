package com.example.naverwebtooncloneproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.naverwebtooncloneproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var toonList = mutableListOf<HomeWebToonData>()

    private var backWaitTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 웹툰들의 정보가 담긴 toonList를 아래 MainHomeFragment 호출할 때 정보를 보내준 뒤
        // recyclerView 어댑터를 연결시켜준다

        for (i in 1 until 84) {
            val name = "sq_0"
            val imageId =
                resources.getIdentifier(name, "drawable", "com.example.naverwebtooncloneproject")
            toonList.add(
                HomeWebToonData(
                    imageId, "Title_$i", "Writer_$i", i.toFloat()
                )
            )
        }

        binding.homeBottomNavigationView.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home_item -> {
                        changeFragment(MainHomeFragment(this@MainActivity, toonList))
                    }
                    R.id.recommend_item -> {
                        changeFragment(MainRecommendFragment())
                    }
                    R.id.bestChallenge_item -> {
                        changeFragment(MainBestChallengeFragment())
                    }
                    R.id.mine_item -> {
                        changeFragment(MainMineFragment())
                    }
                    R.id.more_item -> {
                        changeFragment(MainMoreFragment())
                    }
                }
                true
            }
            selectedItemId = R.id.home_item
        }

    }

    override fun onResume() {
        super.onResume()
    }


    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(binding.homeFrameView.id, fragment).commit()
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