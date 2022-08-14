package com.oliversolutions.dev.usermanager.features.user.domain.usescases

import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.data.models.UserModel
import com.oliversolutions.dev.usermanager.features.user.data.models.asDomainModel
import com.oliversolutions.dev.usermanager.features.user.data.repositories.UserRepositoryImpl
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User

class GetUser(private val userRepository: UserRepositoryImpl) {
    suspend fun call(id: String): Result<User> {
        return when (val result = userRepository.getUser(id)) {
            is Result.Success<*> -> {
                val data = result.data as UserModel
                Result.Success(data.asDomainModel())
            }
            is Result.Error -> result
        }
    }
}