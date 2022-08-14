package com.oliversolutions.dev.usermanager.features.user.data.api

import com.oliversolutions.dev.usermanager.features.user.data.models.UserModel
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.http.*


interface UserApi {
    @GET("/api/User")
    suspend fun getUsers(): List<UserModel>

    @GET("/api/User/{id}")
    suspend fun getUser(@Path("id") id: String): UserModel

    @DELETE("/api/User/{id}")
    suspend fun deleteUser(@Path("id") id: Int)

    @POST("/api/User")
    suspend fun updateUser(@Body requestBody: RequestBody)

    @PUT("/api/User")
    suspend fun createUser(@Body requestBody: RequestBody)
}