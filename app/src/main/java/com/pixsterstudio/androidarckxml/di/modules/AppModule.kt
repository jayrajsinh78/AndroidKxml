package com.pixsterstudio.androidarckxml.di.modules

import android.app.Application
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.pixsterstudio.androidarckxml.session.AppSession
import com.pixsterstudio.androidarckxml.session.Session
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideApplicationContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun getSession(session: AppSession): Session = session


    @Provides
    @Singleton
    fun providesFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

}