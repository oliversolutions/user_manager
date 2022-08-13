package com.oliversolutions.dev.usermanager.features.user.data.api

import com.oliversolutions.dev.usermanager.features.user.data.models.UserModel
import retrofit2.http.GET


interface UserApi {
    @GET("/api/User")
    suspend fun getUsers(): List<UserModel>
}