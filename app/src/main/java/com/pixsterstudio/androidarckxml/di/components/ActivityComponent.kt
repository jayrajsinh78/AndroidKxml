package com.pixsterstudio.androidarckxml.di.components

import com.pixsterstudio.androidarckxml.di.PerActivityScope
import com.pixsterstudio.androidarckxml.di.modules.ActivityModule
import com.pixsterstudio.androidarckxml.di.modules.FragmentModule
import com.pixsterstudio.androidarckxml.ui.auth.activity.AuthenticationActivity
import com.pixsterstudio.androidarckxml.ui.base.BaseActivity
import com.pixsterstudio.androidarckxml.ui.home.activity.HomeActivity
import com.pixsterstudio.androidarckxml.ui.navigation.Navigator
import com.pixsterstudio.androidarckxml.ui.splash.SplashActivity
import dagger.BindsInstance
import dagger.Component

@PerActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun activity(): BaseActivity

    fun navigator(): Navigator

    fun plus(fragmentModule: FragmentModule): FragmentComponent
    fun inject(splashActivity: SplashActivity)
    fun inject(homeActivity: HomeActivity)
    fun inject(authenticationActivity: AuthenticationActivity)

    @Component.Builder
    interface Builder {

        fun bindApplicationComponent(applicationComponent: AppComponent): Builder

        @BindsInstance
        fun bindActivity(baseActivity: BaseActivity): Builder

        fun build(): ActivityComponent
    }



}