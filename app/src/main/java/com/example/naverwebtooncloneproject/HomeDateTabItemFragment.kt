package com.example.naverwebtooncloneproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeDateTabItemFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.viewpager_webtoon_main_tab, container, false)
    }
}

class HomeMainViewPagerAdapter(private val mainActivity: MainActivity, private val toonList: MutableList<HomeWebToonData>) : FragmentStateAdapter(mainActivity) {
    override fun getItemCount(): Int = 10

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeMenuTabNewFragment(mainActivity, toonList)
            1 -> HomeMenuTabMondayFragment(mainActivity, toonList)
            2 -> HomeMenuTabTuesdayFragment(mainActivity, toonList)
            3 -> HomeMenuTabWednesdayFragment(mainActivity, toonList)
            4 -> HomeMenuTabThursdayFragment(mainActivity, toonList)
            5 -> HomeMenuTabFridayFragment(mainActivity, toonList)
            6 -> HomeMenuTabSaturdayFragment(mainActivity, toonList)
            7 -> HomeMenuTabSundayFragment(mainActivity, toonList)
            8 -> HomeMenuTabEverydayFragment(mainActivity, toonList)
            else -> HomeMenuTabFinishedFragment(mainActivity, toonList)
        }
    }
}
