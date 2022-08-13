package com.oliversolutions.dev.usermanager.features.user.domain.repositories

import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User

interface UserRepository {
    suspend fun getUsers() : Result<List<User>>
}
