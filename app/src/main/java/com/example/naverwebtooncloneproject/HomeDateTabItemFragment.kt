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

class HomeMainViewPagerAdapter(private val mainActivity: MainActivity, private val toonList: MutableList<MutableList<HomeWebToonData>>) : FragmentStateAdapter(mainActivity) {
    override fun getItemCount(): Int = 10

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> HomeMenuTabNewFragment(mainActivity, toonList[0])
            1 -> HomeMenuTabMondayFragment(mainActivity, toonList[1])
            2 -> HomeMenuTabTuesdayFragment(mainActivity, toonList[2])
            3 -> HomeMenuTabWednesdayFragment(mainActivity, toonList[3])
            4 -> HomeMenuTabThursdayFragment(mainActivity, toonList[4])
            5 -> HomeMenuTabFridayFragment(mainActivity, toonList[5])
            6 -> HomeMenuTabSaturdayFragment(mainActivity, toonList[6])
            7 -> HomeMenuTabSundayFragment(mainActivity, toonList[7])
            8 -> HomeMenuTabEverydayFragment(mainActivity, toonList[8])
            else -> HomeMenuTabFinishedFragment(mainActivity, toonList[9])
        }
    }
}
