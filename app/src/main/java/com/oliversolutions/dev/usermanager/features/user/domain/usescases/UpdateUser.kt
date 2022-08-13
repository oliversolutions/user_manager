package com.oliversolutions.dev.usermanager.features.user.domain.usescases

import com.google.gson.Gson
import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.data.repositories.UserRepositoryImpl
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User

class UpdateUser(private val userRepository: UserRepositoryImpl) {
    suspend fun call(user: User): Result<Boolean> {
        return userRepository.updateUser(Gson().toJson(user))
    }
}