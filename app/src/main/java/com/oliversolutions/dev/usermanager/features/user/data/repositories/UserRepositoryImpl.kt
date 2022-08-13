package com.oliversolutions.dev.usermanager.features.user.data.repositories

import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.data.datasources.UserRemoteDataSource
import com.oliversolutions.dev.usermanager.features.user.data.models.UserModel
import com.oliversolutions.dev.usermanager.features.user.data.models.asDomainModel
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User
import com.oliversolutions.dev.usermanager.features.user.domain.repositories.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class UserRepositoryImpl(private val userRemoteDataSource: UserRemoteDataSource, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : UserRepository {

    override suspend fun getUsers(): Result<List<User>> = withContext(ioDispatcher)  {
        var result = userRemoteDataSource.getUsers()
        when (result) {
            is Result.Success<*> -> {
                val data = result.data as List<UserModel>
                return@withContext Result.Success(data.map {
                    it.asDomainModel()
                })
            }
            is Result.Error -> return@withContext result
        }
    }
}
