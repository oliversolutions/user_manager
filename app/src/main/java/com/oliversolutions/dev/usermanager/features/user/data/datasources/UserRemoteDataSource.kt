package com.oliversolutions.dev.usermanager.features.user.data.datasources

import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.data.api.RetrofitAdapter
import com.oliversolutions.dev.usermanager.features.user.data.models.UserModel

class UserRemoteDataSource(private val retrofitAdapter: RetrofitAdapter) {
    suspend fun getUsers(): Result<List<UserModel>> {
        try {
            return Result.Success(retrofitAdapter.userApi().getUsers())
        } catch (e: Exception) {
            return Result.Error(e.localizedMessage)
        }
    }
}