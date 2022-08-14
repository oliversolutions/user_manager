package com.oliversolutions.dev.usermanager.features.user.domain.repositories

import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.data.models.UserModel

interface UserRepository {
    suspend fun getUsers() : Result<List<UserModel>>
    suspend fun getUser(id: String) : Result<UserModel>
    suspend fun deleteUser(id: Int) : Result<Boolean>
    suspend fun updateUser(body: String) : Result<Boolean>
    suspend fun createUser(body: String) : Result<Boolean>
}
