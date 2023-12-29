package com.pixsterstudio.androidarckxml.ui.auth.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(var fm: Fragment, var list: ArrayList<Fragment>) :
    FragmentStateAdapter(fm) {
    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment = list[position]

    fun getCurrentFragment(position: Int) :Fragment = list[position]
}