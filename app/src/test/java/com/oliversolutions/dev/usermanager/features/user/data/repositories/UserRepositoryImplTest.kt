package com.oliversolutions.dev.usermanager.features.user.data.repositories

import com.google.gson.Gson
import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.data.datasources.UserRemoteDataSource
import com.oliversolutions.dev.usermanager.features.user.data.models.UserModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class UserRepositoryImplTest {
    private lateinit var userModel: UserModel
    private lateinit var body: String
    private val id = 33
    private lateinit var userModelList: List<UserModel>

    @Before
    fun setup() {
        userModel = UserModel(33, "manolo", "2022-08-14T20:45:00")
        body = Gson().toJson(userModel)
        userModelList = listOf(
            UserModel(33, "hola", "2022-08-14T20:45:00"),
            UserModel(34, "manolo", "2022-08-14T20:45:00")
        )
    }

    @Test
    fun deleteUser_Success() = runBlocking {
        /* Given */
        val mock = mock<UserRemoteDataSource> {
            onBlocking { deleteUser(id) } doReturn Result.Success(true)
        }
        /* When */
        val repository = UserRepositoryImpl(mock)
        val result = repository.deleteUser(id)

        /* Then */
        Mockito.verify(mock).deleteUser(id)
        Assert.assertEquals(result, Result.Success(true))
    }

    @Test
    fun deleteUser_Fail() = runBlocking {
        /* Given */
        val mock = mock<UserRemoteDataSource> {
            onBlocking { deleteUser(id) } doReturn Result.Error("error ocurred")
        }
        /* When */
        val repository = UserRepositoryImpl(mock)
        val result = repository.deleteUser(id)

        /* Then */
        Mockito.verify(mock).deleteUser(id)
        Assert.assertEquals(result, Result.Error("error ocurred"))
    }

    @Test
    fun getUsers_Success() = runBlocking {
        /* Given */
        val mock = mock<UserRemoteDataSource> {
            onBlocking { getUsers() } doReturn Result.Success(userModelList)
        }
        /* When */
        val repository = UserRepositoryImpl(mock)
        val result = repository.getUsers()

        /* Then */
        Mockito.verify(mock).getUsers()
        Assert.assertEquals(result, Result.Success(userModelList))
    }

    @Test
    fun getUsers_Fail() = runBlocking {
        /* Given */
        val mock = mock<UserRemoteDataSource> {
            onBlocking { getUsers() } doReturn Result.Error("error ocurred")
        }
        /* When */
        val repository = UserRepositoryImpl(mock)
        val result = repository.getUsers()

        /* Then */
        Mockito.verify(mock).getUsers()
        Assert.assertEquals(result, Result.Error("error ocurred"))
    }

    @Test
    fun getUser_Success() = runBlocking {
        /* Given */
        val mock = mock<UserRemoteDataSource> {
            onBlocking { getUser(id.toString()) } doReturn Result.Success(userModel)
        }
        /* When */
        val repository = UserRepositoryImpl(mock)
        val result = repository.getUser(id.toString())

        /* Then */
        Mockito.verify(mock).getUser(id.toString())
        Assert.assertEquals(result, Result.Success(userModel))
    }

    @Test
    fun getUser_Fail() = runBlocking {
        /* Given */
        val mock = mock<UserRemoteDataSource> {
            onBlocking { getUser(id.toString()) } doReturn Result.Error("error ocurred")
        }
        /* When */
        val repository = UserRepositoryImpl(mock)
        val result = repository.getUser(id.toString())

        /* Then */
        Mockito.verify(mock).getUser(id.toString())
        Assert.assertEquals(result, Result.Error("error ocurred"))
    }

    @Test
    fun updateUser_Success() = runBlocking {
        /* Given */
        val mock = mock<UserRemoteDataSource> {
            onBlocking { updateUser(body) } doReturn Result.Success(true)
        }
        /* When */
        val repository = UserRepositoryImpl(mock)
        val result = repository.updateUser(body)
        /* Then */
        Mockito.verify(mock).updateUser(body)
        Assert.assertEquals(result, Result.Success(true))
    }

    @Test
    fun updateUser_Fail() = runBlocking {
        /* Given */
        val mock = mock<UserRemoteDataSource> {
            onBlocking { updateUser(body) } doReturn Result.Error("error found")
        }
        /* When */
        val repository = UserRepositoryImpl(mock)
        val result = repository.updateUser(body)
        /* Then */
        Mockito.verify(mock).updateUser(body)
        Assert.assertEquals(result, Result.Error("error found"))
    }
}