package com.oliversolutions.dev.usermanager.features.user.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitAdapter {
    private val moshi: Moshi = Moshi.Builder()
       .add(KotlinJsonAdapterFactory())
       .build()
    const val API_URL = "https://hello-world.innocv.com/"
    private val userRetrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(API_URL)
        .build()
    fun userApi(): UserApi = userRetrofit.create(UserApi::class.java)
}
