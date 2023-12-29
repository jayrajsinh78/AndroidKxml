package com.pixsterstudio.androidarckxml.di

import android.app.Application
import com.pixsterstudio.androidarckxml.di.components.AppComponent
import com.pixsterstudio.androidarckxml.di.components.DaggerAppComponent

enum class Injector private constructor() {
    INSTANCE;

    lateinit var appComponent: AppComponent
        internal set

    fun initAppComponent(application: Application) {
        appComponent = DaggerAppComponent.builder()
                .application(application)
                .build()
    }
}
