package com.pixsterstudio.androidarckxml.di.components

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.pixsterstudio.androidarckxml.data.repo.HomeRepository
import com.pixsterstudio.androidarckxml.di.App
import com.pixsterstudio.androidarckxml.di.modules.AppModule
import com.pixsterstudio.androidarckxml.di.modules.NetModule
import com.pixsterstudio.androidarckxml.di.modules.ServiceModule
import com.pixsterstudio.androidarckxml.di.modules.ViewModelModule
import com.pixsterstudio.androidarckxml.session.AppPreferences
import com.pixsterstudio.androidarckxml.session.Session
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class,ServiceModule::class,ViewModelModule::class])
interface AppComponent {

    fun inject(appRef:App)

    fun context(): Context

    fun provideSession(): Session

    fun provideViewModelFactory(): ViewModelProvider.Factory

    fun provideHomeRepository(): HomeRepository

    fun provideAppPreferences(): AppPreferences

    @Component.Builder
    interface AppComponentBuilder {
        @BindsInstance
        fun application(application: Application): AppComponentBuilder

        fun build(): AppComponent
    }
}