package com.oliversolutions.dev.usermanager.features.user.data.datasources

import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.data.api.RetrofitAdapter
import com.oliversolutions.dev.usermanager.features.user.data.models.UserModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class UserRemoteDataSource(private val retrofitAdapter: RetrofitAdapter) {
    suspend fun getUsers(): Result<List<UserModel>> {
        return try {
            Result.Success(retrofitAdapter.userApi().getUsers())
        } catch (e: Exception) {
            Result.Error(e.localizedMessage!!)
        }
    }

    suspend fun getUser(id: String): Result<UserModel> {
        return try {
            Result.Success(retrofitAdapter.userApi().getUser(id))
        } catch (e: Exception) {
            Result.Error(e.localizedMessage!!)
        }
    }

    suspend fun deleteUser(id: Int): Result<Boolean> {
        return try {
            retrofitAdapter.userApi().deleteUser(id)
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage!!)
        }
    }

    suspend fun updateUser(body: String): Result<Boolean> {
        return try {
            retrofitAdapter.userApi().updateUser(
                body.toRequestBody("application/json; charset=utf-8".toMediaType())
            )
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage!!)
        }
    }

    suspend fun createUser(body: String): Result<Boolean> {
        return try {
            retrofitAdapter.userApi().createUser(
                body.toRequestBody("application/json; charset=utf-8".toMediaType())
            )
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e.localizedMessage!!)
        }
    }
}