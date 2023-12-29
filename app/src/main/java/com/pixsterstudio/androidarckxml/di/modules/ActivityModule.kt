package com.pixsterstudio.androidarckxml.di.modules

import androidx.fragment.app.FragmentManager
import com.pixsterstudio.androidarckxml.di.PerActivityScope
import com.pixsterstudio.androidarckxml.ui.base.BaseActivity
import com.pixsterstudio.androidarckxml.ui.navigation.FragmentHandler
import com.pixsterstudio.androidarckxml.ui.navigation.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ActivityModule {

    @Provides
    @PerActivityScope
    internal fun navigator(activity: BaseActivity): Navigator {
        return activity
    }

    @Provides
    @PerActivityScope
    @Named("placeholder")
    internal fun placeHolder(baseActivity: BaseActivity): Int {
        return baseActivity.findFragmentPlaceHolder()
    }

    @Provides
    @PerActivityScope
    internal fun provideFragmentManager(baseActivity: BaseActivity):FragmentManager{
        return baseActivity.supportFragmentManager
    }

    @Provides
    @PerActivityScope
    internal fun provideFragmentHandler(fragmentManager: com.pixsterstudio.androidarckxml.ui.navigation.FragmentManager):FragmentHandler{
        return fragmentManager
    }

}