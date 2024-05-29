package com.renad.nytpulse.data.di

import com.renad.nytpulse.BuildConfig
import com.renad.nytpulse.core.interceptors.ApiKeyInterceptor
import com.renad.nytpulse.data.source.remote.NYTApiService
import com.renad.nytpulse.data.source.remote.ServiceBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory =
        MoshiConverterFactory.create(
            Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build(),
        )

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient
            .Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY),
            )
            .addInterceptor(ApiKeyInterceptor(BuildConfig.A_K))
            .build()

    @Provides
    @Singleton
    fun provideRetrofitClientForNYTApi(
        okHttp: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory,
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .client(okHttp)
            .baseUrl("https://api.nytimes.com/svc/")
            .build()

    @Provides
    @Singleton
    fun provideNYTApiService(serviceBuilder: ServiceBuilder): NYTApiService =
        serviceBuilder.buildService(NYTApiService::class.java)
}
