package com.jsn.quickgrab.di.module

import android.content.Context
import com.google.firebase.BuildConfig
import com.jsn.quickgrab.data.remote.api.ApiService
import com.jsn.quickgrab.di.qualifier.RetrofitWithLogging
import com.jsn.quickgrab.di.qualifier.RetrofitWithoutLogging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //others are: ActivityComponent, FragmentComponent, ViewModelComponent, ServiceComponent
object NetworkModule {
    private const val BASE_URL = "https://www.example.com/"

    @Provides
    @Singleton
    @RetrofitWithLogging
    fun providesRetrofit(@ApplicationContext context: Context): Retrofit {
        val okHttpClient =
            OkHttpClient.Builder().addInterceptor(provideLoggingInterceptor()).addInterceptor(
                provideCommonHeadersInterceptor()
            ).build()

        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    @RetrofitWithoutLogging
    fun providesRetrofitWithoutLogging(@ApplicationContext context: Context): Retrofit {
        val okHttpClient =
            OkHttpClient.Builder().addInterceptor(
                provideCommonHeadersInterceptor()
            ).build()

        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun provideLoggingInterceptor(): Interceptor {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        } else {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    private fun provideCommonHeadersInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val modifiedRequest =
                originalRequest.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    //.addHeader("Authorization", "Bearer ${myToken}")
                    .build()
            chain.proceed(modifiedRequest)
        }
    }

    @Provides
    @Singleton
    fun provideApiService(@RetrofitWithLogging retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}