package com.pixsterstudio.androidarckxml.di.modules

import com.pixsterstudio.androidarckxml.exception.ServerError
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [(AppModule::class)])
class NetModule {

    @Singleton
    @Provides
    internal fun provideClient(/*@Named("header") headerInterceptor: Interceptor,*/
                               @Named("pre_validation") networkInterceptor: Interceptor): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
               // .addInterceptor(headerInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(networkInterceptor)
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://us-central1-autocaption-app.cloudfunctions.net/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

/*    @Provides
    @Singleton
    @Named("header")
    internal fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val build = chain.request().newBuilder()
                    .addHeader(Session.AUTHORIZATION, Session.API_TOKEN)
                    .header(Session.CONTENT_TYPE, Session.CONTENT_AUDIO)
                    .build()
            chain.proceed(build)
        }
    }*/

    @Provides
    @Singleton
    @Named("pre_validation")
    internal fun provideNetworkInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val code = response.code
            if (code >= 500)
                throw ServerError(
                    "Unknown server error",
                    response.body.string()
                )
            else if (code == 401 || code == 403)
                throw ServerError("Exception $code",response.code.toString())
            response
        }
    }


}
