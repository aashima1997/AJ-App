package com.example.ajapp.Menu.ui.Fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.ajapp.R
import com.example.ajapp.Menu.ui.Adapter.TabAdapter
import com.google.android.material.tabs.TabLayout

@Suppress("DEPRECATION")
class Tabs : Fragment() {

    lateinit var tabLayout:TabLayout
    lateinit var viewpager:ViewPager
    lateinit var toolbar1: Toolbar

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_tabs, container, false)

        val tabLayout=view!!.findViewById<TabLayout>(R.id.tablayout)
        val viewPager=view!!.findViewById<ViewPager>(R.id.viewPager)
        tabLayout.addTab(tabLayout.newTab().setText("Gallery"))
        tabLayout.addTab(tabLayout.newTab().setText("Quotes"))
        val adapter= getFragmentManager()?.let { TabAdapter(it, tabLayout.tabCount) }
        viewPager.adapter=adapter

        //changing the tab  on clicking the tab icon
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
//when changing tab with swipe
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        return view
    }



}


