package com.oliversolutions.dev.usermanager.features.user.domain.usecases

import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.data.repositories.UserRepositoryImpl
import com.oliversolutions.dev.usermanager.features.user.domain.usescases.DeleteUser
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class DeleteUserTest {

    private var id = 33

    @Test
    fun deleteUser_Success () = runBlocking {
        /* Given */
        val mock = mock<UserRepositoryImpl> {
            onBlocking { deleteUser(id) } doReturn Result.Success(true)
        }
        /* When */
        val usecase = DeleteUser(mock)
        val result = usecase.call(id)

        /* Then */
        Mockito.verify(mock).deleteUser(id)
        Assert.assertEquals(result, Result.Success(true))
    }

    @Test
    fun deleteUser_Fail() = runBlocking {
        /* Given */
        val mock = mock<UserRepositoryImpl> {
            onBlocking { deleteUser(id) } doReturn Result.Error("error found")
        }
        /* When */
        val usecase = DeleteUser(mock)
        val result = usecase.call(id)

        /* Then */
        Mockito.verify(mock).deleteUser(id)
        Assert.assertEquals(result, Result.Error("error found"))
    }
}