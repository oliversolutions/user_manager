package com.oliversolutions.dev.usermanager.features.user.domain.usecases

import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.data.models.UserModel
import com.oliversolutions.dev.usermanager.features.user.data.models.asDomainModel
import com.oliversolutions.dev.usermanager.features.user.data.repositories.UserRepositoryImpl
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User
import com.oliversolutions.dev.usermanager.features.user.domain.usescases.GetUser
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
class GetUserTest {

    private lateinit var userModel: UserModel
    private var id = "33"

    @Before
    fun setup() {
        userModel = UserModel(33, "hola", "2022-08-14T20:45:00")
    }

    @Test
    fun getUser_Success () = runBlocking {
        /* Given */
        val mock = mock<UserRepositoryImpl> {
            onBlocking { getUser(id) } doReturn Result.Success(userModel)
        }
        /* When */
        val usecase = GetUser(mock)
        val result = usecase.call(id)

        /* Then */
        Mockito.verify(mock).getUser(id)
        if (result is Result.Success<*>) {
            val user = result.data as User
            Assert.assertEquals(user.id, userModel.asDomainModel().id)
            Assert.assertEquals(user.name, userModel.asDomainModel().name)
            Assert.assertEquals(user.birthdate.dateString, userModel.asDomainModel().birthdate.dateString)
        }
    }

    @Test
    fun getUser_Fail() = runBlocking {
        /* Given */
        val mock = mock<UserRepositoryImpl> {
            onBlocking { getUser(id) } doReturn Result.Error("error found")
        }
        /* When */
        val usecase = GetUser(mock)
        val result = usecase.call(id)
        /* Then */
        Mockito.verify(mock).getUser(id)
        Assert.assertEquals(result, Result.Error("error found"))
    }
}