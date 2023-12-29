package com.pixsterstudio.androidarckxml.di

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App:Application() {

   companion object {
       @SuppressLint("StaticFieldLeak")
       var mContext:Context?=null
   }

    fun getContext():Context{
        return mContext!!
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
        Injector.INSTANCE.initAppComponent(this)
    }
}