package com.example.naverwebtooncloneproject

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeMenuTabSaturdayFragment(private val mainActivity: MainActivity, private val toonList: MutableList<HomeWebToonData>) : Fragment() {
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_home_tab_viewpager, container, false)

        val recyclerAdapter = HomeWebToonRecyclerViewAdapter(mContext, toonList)
        val rccView = view.findViewById<RecyclerView>(R.id.home_tab_recyclerview)

        rccView.layoutManager = GridLayoutManager(mainActivity, 3)
        rccView.adapter = recyclerAdapter

        return view
    }
}