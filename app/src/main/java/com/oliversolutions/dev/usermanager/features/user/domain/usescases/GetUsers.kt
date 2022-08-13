package com.oliversolutions.dev.usermanager.features.user.domain.usescases

import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.data.models.UserModel
import com.oliversolutions.dev.usermanager.features.user.data.models.asDomainModel
import com.oliversolutions.dev.usermanager.features.user.data.repositories.UserRepositoryImpl
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User

class GetUsers(private val userRepository: UserRepositoryImpl) {
    suspend fun call() : Result<List<User>> {
        val result = userRepository.getUsers()
        when (result) {
            is Result.Success<*> -> {
                val data = result.data as List<UserModel>
                return Result.Success(data.map {
                    it.asDomainModel()
                })
            }
            is Result.Error -> return result
        }
    }
}