package com.pixsterstudio.androidarckxml.di.modules

import com.pixsterstudio.androidarckxml.di.PerFragmentScope
import com.pixsterstudio.androidarckxml.ui.base.BaseFragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val baseFragment: BaseFragment<*>) {

    @Provides
    @PerFragmentScope
    internal fun provideBaseFragment(): BaseFragment<*> {
        return baseFragment
    }

}
