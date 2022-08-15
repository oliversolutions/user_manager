package com.oliversolutions.dev.usermanager.features.user.domain.usecases

import com.google.gson.Gson
import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.data.repositories.UserRepositoryImpl
import com.oliversolutions.dev.usermanager.features.user.domain.entities.DateTime
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User
import com.oliversolutions.dev.usermanager.features.user.domain.entities.asDataModel
import com.oliversolutions.dev.usermanager.features.user.domain.usescases.CreateUser
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.junit.Assert.assertEquals
import org.mockito.Mockito

@RunWith(MockitoJUnitRunner::class)
class CreateUserTest {

    private lateinit var user: User
    private lateinit var body: String

    @Before
    fun setup() {
        user = User("hola", DateTime("2022-08-14T20:45:00"), 0)
        body = Gson().toJson(user.asDataModel())
    }

    @Test
    fun createNewUser_Success() = runBlocking {
        /* Given */
        val mock = mock<UserRepositoryImpl> {
            onBlocking { createUser(body) } doReturn Result.Success(true)
        }
        /* When */
        val usecase = CreateUser(mock)
        val result = usecase.call(user)

        /* Then */
        Mockito.verify(mock).createUser(body)
        assertEquals(result, Result.Success(true))
    }

    @Test
    fun createNewUser_Fail() = runBlocking {
        /* Given */
        val mock = mock<UserRepositoryImpl> {
            onBlocking { createUser(body) } doReturn Result.Error("error found")
        }
        /* When */
        val usecase = CreateUser(mock)
        val result = usecase.call(user)

        /* Then */
        Mockito.verify(mock).createUser(body)
        assertEquals(result, Result.Error("error found"))
    }
}