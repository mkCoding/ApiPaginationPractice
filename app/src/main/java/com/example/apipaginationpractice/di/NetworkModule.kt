package com.example.apipaginationpractice.di

import com.example.apipaginationpractice.data.api.ApiDetails
import com.example.apipaginationpractice.data.api.TopAnimeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    // Provide Retrofit
    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiDetails.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // Provide TopAnimeApi
    @Provides
    @Singleton
    fun providesTopAnimeApi(retrofit: Retrofit): TopAnimeApi {
        return retrofit.create(TopAnimeApi::class.java)
    }


}