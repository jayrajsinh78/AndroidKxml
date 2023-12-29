package com.pixsterstudio.androidarckxml.di.modules

import com.pixsterstudio.androidarckxml.data.livedata.HomeLiveDataSource
import com.pixsterstudio.androidarckxml.data.repo.HomeRepository
import com.pixsterstudio.androidarckxml.data.service.HomeService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun provideHomeService(retrofit: Retrofit): HomeService {
        return retrofit.create(HomeService::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(liveData: HomeLiveDataSource):HomeRepository{
        return liveData
    }


}