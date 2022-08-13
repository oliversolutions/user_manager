package com.oliversolutions.dev.usermanager.features.user.domain.usescases

import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.data.repositories.UserRepositoryImpl
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User

class GetUsers(private val userRepository: UserRepositoryImpl) {
    suspend fun call() : Result<List<User>> {
        return userRepository.getUsers()
    }
}