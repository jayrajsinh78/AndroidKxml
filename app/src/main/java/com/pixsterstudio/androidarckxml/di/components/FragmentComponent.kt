package com.pixsterstudio.androidarckxml.di.components

import com.pixsterstudio.androidarckxml.di.PerFragmentScope
import com.pixsterstudio.androidarckxml.di.modules.FragmentModule
import com.pixsterstudio.androidarckxml.ui.base.BaseFragment
import com.pixsterstudio.androidarckxml.ui.home.fragment.WebViewFragment
import dagger.Subcomponent

@PerFragmentScope
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {
    fun baseFragment(): BaseFragment<*>
    fun inject(webViewFragment: WebViewFragment)

}