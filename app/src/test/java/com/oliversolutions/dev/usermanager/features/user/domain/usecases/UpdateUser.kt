package com.oliversolutions.dev.usermanager.features.user.domain.usecases

import com.google.gson.Gson
import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.data.repositories.UserRepositoryImpl
import com.oliversolutions.dev.usermanager.features.user.domain.entities.DateTime
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User
import com.oliversolutions.dev.usermanager.features.user.domain.entities.asDataModel
import com.oliversolutions.dev.usermanager.features.user.domain.usescases.UpdateUser
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
class UpdateUserTest {

    private lateinit var user: User
    private lateinit var body: String

    @Before
    fun setup() {
        user = User("hola", DateTime("2022-08-14T20:45:00"), 33)
        body = Gson().toJson(user.asDataModel())
    }

    @Test
    fun updateUser_Success () = runBlocking {
        /* Given */
        val mock = mock<UserRepositoryImpl> {
            onBlocking { updateUser(body) } doReturn Result.Success(true)
        }
        /* When */
        val usecase = UpdateUser(mock)
        val result = usecase.call(user)
        /* Then */
        Mockito.verify(mock).updateUser(body)
        Assert.assertEquals(result, Result.Success(true))
    }

    @Test
    fun updateUser_Fail() = runBlocking {
        /* Given */
        val mock = mock<UserRepositoryImpl> {
            onBlocking { updateUser(body) } doReturn Result.Error("error found")
        }
        /* When */
        val usecase = UpdateUser(mock)
        val result = usecase.call(user)
        /* Then */
        Mockito.verify(mock).updateUser(body)
        Assert.assertEquals(result, Result.Error("error found"))
    }
}