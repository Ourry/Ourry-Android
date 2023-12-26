package com.worldonetop.ourry.di.data

import android.content.Context
import com.google.gson.GsonBuilder
import com.worldonetop.ourry.di.manager.network.ApiCallAdapter
import com.worldonetop.ourry.di.manager.network.ApiInterceptor
import com.worldonetop.ourry.source.remote.OurryAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    const val URL = "http://10.0.2.2:3000" // localhost

    private val GSON = GsonConverterFactory.create(
        GsonBuilder()
            .setLenient()
            .create()
    )

    @Singleton
    @Provides
    fun provideOurryService() = Retrofit.Builder()
        .baseUrl(URL)
        .client(provideOkHttpClient())
        .addCallAdapterFactory(ApiCallAdapter.ApiCallAdapterFactory())
        .addConverterFactory(GSON)
        .build()
        .create(OurryAPI::class.java)
//
//    @Singleton
//    @Provides
//    fun provideLoginService(@ApplicationContext context: Context): LoginAPI {
//        return Retrofit.Builder()
//            .baseUrl(URL)
//            .addConverterFactory(GSON)
//            .addCallAdapterFactory(ApiCallAdapter.ApiCallAdapterFactory(context))
//            .build()
//            .create(LoginAPI::class.java)
//    }

    private fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(ApiInterceptor())
            .addInterceptor(logger)
            .build()
    }

}