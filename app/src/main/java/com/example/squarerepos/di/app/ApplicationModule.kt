package com.example.squarerepos.di.app

import android.content.Context
import com.example.squarerepos.App
import com.example.squarerepos.BuildConfig
import com.example.squarerepos.data.ReposDataSource
import com.example.squarerepos.data.ReposRepository
import com.example.squarerepos.data.local.ReposLocalDataSource
import com.example.squarerepos.data.remote.ReposApi
import com.example.squarerepos.data.remote.ReposRemoteDataSource
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class ApplicationModule {

    @Provides
    fun provideContext(application: App): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideBooksApi(
        retrofit: Retrofit
    ): ReposApi {
        return retrofit.create(ReposApi::class.java)
    }

    @Provides
    @Named(REPOS_REMOTE_DATA_SOURCE)
    fun provideBooksRemoteDataSource(
        reposApi: ReposApi
    ): ReposDataSource {
        return ReposRemoteDataSource(reposApi)
    }

    @Provides
    @Named(REPOS_LOCAL_DATA_SOURCE)
    fun provideBooksLocalDataSource(

    ): ReposDataSource {
        return ReposLocalDataSource()
    }

    @Provides
    @Singleton
    fun provideBooksRepository(
        @Named(REPOS_REMOTE_DATA_SOURCE) remoteDataSource: ReposDataSource,
        @Named(REPOS_LOCAL_DATA_SOURCE) localDataSource: ReposDataSource
    ): ReposRepository {
        return ReposRepository(remoteDataSource, localDataSource)
    }

    companion object {

        const val BASE_URL = BuildConfig.BASE_URL
        const val REPOS_REMOTE_DATA_SOURCE = "reposRemoteDataSource"
        const val REPOS_LOCAL_DATA_SOURCE = "reposLocalDataSource"


    }
}