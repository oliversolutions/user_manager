package com.oliversolutions.dev.usermanager.features.user.presentation.viewModels

import androidx.test.core.app.ApplicationProvider
import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.domain.entities.DateTime
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User
import com.oliversolutions.dev.usermanager.features.user.domain.usescases.GetUsers
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class UsersViewModelTest {

    private lateinit var users: List<User>
    @Before
    fun setup() {
        users = listOf(User("hola", DateTime("2022-08-14T20:45:00"), 33))
    }

    @Test
    fun setUsers_callLoadingForSuccess () {
        /* Given */
        val mock = mock<GetUsers> {
            onBlocking { call() } doReturn Result.Success(users)
        }
        /* When */
        val viewModel = UsersViewModel(ApplicationProvider.getApplicationContext(), mock)
        viewModel.setUsers()
        MatcherAssert.assertThat(viewModel.showLoading.value, CoreMatchers.`is`(false))
    }

    @Test
    fun setUsers_callLoadingForFail () {
        /* Given */
        val mock = mock<GetUsers> {
            onBlocking { call() } doReturn Result.Error("error ocurred")
        }
        /* When */
        val viewModel = UsersViewModel(ApplicationProvider.getApplicationContext(), mock)
        viewModel.setUsers()
        /* Then */
        MatcherAssert.assertThat(viewModel.showLoading.value, CoreMatchers.`is`(false))
    }
}