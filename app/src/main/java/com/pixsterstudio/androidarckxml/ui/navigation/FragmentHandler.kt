package com.pixsterstudio.androidarckxml.ui.navigation

import com.pixsterstudio.androidarckxml.ui.base.BaseFragment

interface FragmentHandler {

    fun openFragment(baseFragment:BaseFragment<*>,option:Option,isAddToBackStack:Boolean,tag:String)

    fun showFragment(fragmentToShow: BaseFragment<*>, vararg fragmentToHide: BaseFragment<*>)

    fun  clearFragmentHistory(tag:String)
}