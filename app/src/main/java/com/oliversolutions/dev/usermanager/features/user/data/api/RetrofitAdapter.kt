package com.oliversolutions.dev.usermanager.features.user.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object RetrofitAdapter {
    val headers = mapOf(
        "content-type" to "application/json"
    )
    private val userRetrofit: Retrofit
    private val moshi: Moshi
    const val API_URL = "https://hello-world.innocv.com/"

    init {
         moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        userRetrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(API_URL)
            .build()
    }
    fun userApi(): UserApi = userRetrofit.create(UserApi::class.java)
}
