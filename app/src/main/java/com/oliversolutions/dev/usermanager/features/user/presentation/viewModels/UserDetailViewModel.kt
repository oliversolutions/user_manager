package com.oliversolutions.dev.usermanager.features.user.presentation.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.oliversolutions.dev.usermanager.R
import com.oliversolutions.dev.usermanager.core.base.BaseViewModel
import com.oliversolutions.dev.usermanager.core.base.NavigationCommand
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User
import com.oliversolutions.dev.usermanager.features.user.domain.usescases.DeleteUser
import com.oliversolutions.dev.usermanager.features.user.domain.usescases.UpdateUser
import kotlinx.coroutines.launch
import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.presentation.fragments.UserDetailFragmentDirections

class UserDetailViewModel(
    val app: Application,
    private val updateUserUsecase: UpdateUser,
    private val deleteUserUseCase: DeleteUser) : BaseViewModel(app) {

    fun updateUser(user: User) {
        viewModelScope.launch {
            when (val result = updateUserUsecase.call(user)) {
                is Result.Success<*> -> {
                    showSnackBar.value = app.getString(R.string.user_has_been_updated)
                    navigationCommand.value = NavigationCommand.To(UserDetailFragmentDirections.actionUserDetailFragmentToUsersFragment())
                }
                is Result.Error -> {
                    showSnackBar.value = app.getString(R.string.unexpected_error)
                    Log.i(UserDetailViewModel::class.java.simpleName, result.message)
                }
            }
        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            when (val result = deleteUserUseCase.call(id)) {
                is Result.Success<*> -> {
                    showSnackBar.value = app.getString(R.string.user_has_been_deleted)
                    navigationCommand.value = NavigationCommand.To(UserDetailFragmentDirections.actionUserDetailFragmentToUsersFragment())
                }
                is Result.Error -> {
                    showSnackBar.value = app.getString(R.string.unexpected_error)
                    Log.i(UserDetailViewModel::class.java.simpleName, result.message)

                }
            }
        }
    }
}