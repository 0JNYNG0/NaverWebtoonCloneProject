package com.example.naverwebtooncloneproject

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.abs
import kotlin.math.max

class BN_FG_Home_Fragment(private val mContext: MainActivity, private val toonList: MutableList<HomeWebToonData>) : Fragment() {
    private val minAlpha = 0.5f
    private val minScale = 0.99f
    private val totalNumBanner = 20
    private lateinit var mainViewpager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_foreground_home, container, false)

        val tabLayout: TabLayout = view.findViewById(R.id.home_date_tab)
        val topViewPager: ViewPager2 = view.findViewById(R.id.home_top_viewpager)
        val viewPagerAdapter = HomeMainViewPagerAdapter(mContext, toonList)
        val topViewPagerAdapter = HomeTopViewpagerAdapter(mContext)

        mainViewpager = view.findViewById(R.id.home_main_grid_viewpager)
        // main_웹툰 Viewpager 어댑터 연결
        mainViewpager.adapter = viewPagerAdapter
        //mainViewpager.isUserInputEnabled = false
        // main_상단 Viewpager 어댑터 연결
        topViewPager.adapter = topViewPagerAdapter

        topViewPager.setPageTransformer(MarginPageTransformer(40))
        topViewPager.setPageTransformer(ZoomOutPageTransformer())

        //topViewPager.adapter = HomeTopViewpagerAdapter(getBannerList())
        topViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val currentBanner = view.findViewById<TextView>(R.id.see_all_current_banner)
        val totalBanner = view.findViewById<TextView>(R.id.see_all_total_banner)
        val seeAllEllipse = view.findViewById<LinearLayout>(R.id.home_top_see_all_ellipse)

        totalBanner.text = totalNumBanner.toString()
        topViewPager.apply {
            registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    val pos = position + 1
                    currentBanner.text = "$pos"
                }
            })
        }
        seeAllEllipse.setOnClickListener {
            Toast.makeText(mContext, "모두 보기", Toast.LENGTH_SHORT).show()
        }

        val homeSearch = view.findViewById<ImageButton>(R.id.home_search_icon)
        homeSearch.setOnClickListener {
            val intent = Intent(mContext, SearchActivity::class.java)
            startActivity(intent)
        }


        val dateTitleTab = listOf("신작","월","화","수","목","금","토","일","매일+","완결")

        TabLayoutMediator(tabLayout, mainViewpager) {
                tab, position -> tab.text = dateTitleTab[position]
        }.attach()

        return view
    }

    override fun onStart() {
        super.onStart()

        // 바텀 네비게이션 뷰 간 전환 오류 임시 대처 코드
        mainViewpager.currentItem = mContext.currentDay


    }

    override fun onStop() {
        super.onStop()
            mContext.currentDay = mainViewpager.currentItem
    }


    inner class ZoomOutPageTransformer : ViewPager2.PageTransformer {
        override fun transformPage(view: View, position: Float) {
            view.apply {
                //val pageWidth = width
                //val pageHeight = height
                when {
                    position < -1 -> {
                        alpha = 0f
                    }
                    position <= 1 -> {
                        val scaleFactor = max(minScale, 1 - abs(position))

                        scaleX = scaleFactor
                        scaleY = scaleFactor

                        alpha = (minAlpha
                                +((scaleFactor - minScale) / (1 - minScale)) * (1 - minAlpha))
                    }
                    else -> {
                        alpha = 0f
                    }
                }
            }
        }
    }

}
