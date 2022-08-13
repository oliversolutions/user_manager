package com.oliversolutions.dev.usermanager.features.user.data.datasources

import android.util.Log
import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.data.api.RetrofitAdapter
import com.oliversolutions.dev.usermanager.features.user.data.models.UserModel
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class UserRemoteDataSource(private val retrofitAdapter: RetrofitAdapter) {
    suspend fun getUsers(): Result<List<UserModel>> {
        try {
            return Result.Success(retrofitAdapter.userApi().getUsers())
        } catch (e: Exception) {
            return Result.Error(e.localizedMessage)
        }
    }

    suspend fun getUser(id: String): Result<UserModel> {
        try {
            return Result.Success(retrofitAdapter.userApi().getUser(id))
        } catch (e: Exception) {
            return Result.Error(e.localizedMessage)
        }
    }

    suspend fun deleteUser(id: String): Result<Boolean> {
        try {
            retrofitAdapter.userApi().deleteUser(id)
            return Result.Success(true)
        } catch (e: Exception) {
            return Result.Error(e.localizedMessage)
        }
    }

    suspend fun updateUser(body: String): Result<Boolean> {
        try {
            retrofitAdapter.userApi().updateUser(
                body.toRequestBody("application/json; charset=utf-8".toMediaType())
            )
            return Result.Success(true)
        } catch (e: Exception) {
            return Result.Error(e.localizedMessage)
        }
    }

    suspend fun createUser(body: String): Result<Boolean> {
        try {
            retrofitAdapter.userApi().createUser(
                body.toRequestBody("application/json; charset=utf-8".toMediaType())
            )
            return Result.Success(true)
        } catch (e: Exception) {
            return Result.Error(e.localizedMessage)
        }
    }


}