package com.oliversolutions.dev.usermanager.features.user.domain.usescases

import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.data.repositories.UserRepositoryImpl

class DeleteUser(private val userRepository: UserRepositoryImpl) {
    suspend fun call(id: String): Result<Boolean> {
        return userRepository.deleteUser(id)
    }
}