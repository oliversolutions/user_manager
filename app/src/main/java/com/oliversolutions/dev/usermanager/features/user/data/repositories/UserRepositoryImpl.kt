package com.oliversolutions.dev.usermanager.features.user.data.repositories

import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.data.datasources.UserRemoteDataSource
import com.oliversolutions.dev.usermanager.features.user.data.models.UserModel
import com.oliversolutions.dev.usermanager.features.user.domain.repositories.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : UserRepository {

    override suspend fun getUsers(): Result<List<UserModel>> = withContext(ioDispatcher)  {
        return@withContext userRemoteDataSource.getUsers()
    }

    override suspend fun getUser(id: String): Result<UserModel> = withContext(ioDispatcher)  {
        return@withContext userRemoteDataSource.getUser(id)
    }

    override suspend fun deleteUser(id: Int): Result<Boolean> = withContext(ioDispatcher)  {
        return@withContext userRemoteDataSource.deleteUser(id)
    }

    override suspend fun updateUser(body: String): Result<Boolean> = withContext(ioDispatcher)  {
        return@withContext userRemoteDataSource.updateUser(body)
    }

    override suspend fun createUser(body: String): Result<Boolean> = withContext(ioDispatcher)  {
        return@withContext userRemoteDataSource.createUser(body)
    }
}
