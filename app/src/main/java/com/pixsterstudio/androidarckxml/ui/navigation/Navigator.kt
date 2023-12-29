package com.pixsterstudio.androidarckxml.ui.navigation

import com.pixsterstudio.androidarckxml.ui.base.BaseActivity
import com.pixsterstudio.androidarckxml.ui.base.BaseFragment

interface Navigator {

    fun  <T:BaseFragment<*>> load(tClass: Class<T>): FragmentActionPerformer<T>

    fun loadActivity(aClass: Class<out BaseActivity>): ActivityHandler

    fun goBack()

    fun finish()


}
