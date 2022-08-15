package com.oliversolutions.dev.usermanager.features.user.domain.usecases

import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.data.models.UserModel
import com.oliversolutions.dev.usermanager.features.user.data.models.asDomainModel
import com.oliversolutions.dev.usermanager.features.user.data.repositories.UserRepositoryImpl
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User
import com.oliversolutions.dev.usermanager.features.user.domain.usescases.GetUsers
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
class GetUsersTest {

    private lateinit var userModelList: List<UserModel>

    @Before
    fun setup() {
        userModelList = listOf(
            UserModel(33, "hola", "2022-08-14T20:45:00"),
            UserModel(34, "manolo", "2022-08-14T20:45:00")
        )
    }

    @Test
    fun getUsers_Success () = runBlocking {
        /* Given */
        val mock = mock<UserRepositoryImpl> {
            onBlocking { getUsers() } doReturn Result.Success(userModelList)
        }
        /* When */
        val usecase = GetUsers(mock)
        val result = usecase.call()

        /* Then */
        Mockito.verify(mock).getUsers()
        if (result is Result.Success<*>) {
            val users = result.data as List<User>
            users.forEachIndexed { index, user ->
                Assert.assertEquals(user.id, userModelList[index].asDomainModel().id)
                Assert.assertEquals(user.name, userModelList[index].asDomainModel().name)
                Assert.assertEquals(user.birthdate.dateString, userModelList[index].asDomainModel().birthdate.dateString)
            }
        }
    }

    @Test
    fun getUsers_Fail() = runBlocking {
        /* Given */
        val mock = mock<UserRepositoryImpl> {
            onBlocking { getUsers() } doReturn Result.Error("error found")
        }
        /* When */
        val usecase = GetUsers(mock)
        val result = usecase.call()
        /* Then */
        Mockito.verify(mock).getUsers()
        Assert.assertEquals(result, Result.Error("error found"))
    }
}