package com.example.naverwebtooncloneproject

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class HomeTopViewPagerFragment(private val position: Int) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_home_top_viewpager, container, false)

        when(position % 3) {
            0 -> view.setBackgroundColor(Color.parseColor("#b9fffc"))
            1 -> view.setBackgroundColor(Color.parseColor("#a3d8f4"))
            2 -> view.setBackgroundColor(Color.parseColor("#9ab3f5"))
        }

        return view
    }

}

class HomeTopViewpagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun createFragment(position: Int): Fragment {
        return HomeTopViewPagerFragment(position)
    }
}