package com.oliversolutions.dev.usermanager.features.user.presentation.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.oliversolutions.dev.usermanager.core.base.BaseViewModel
import com.oliversolutions.dev.usermanager.core.base.NavigationCommand
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User
import com.oliversolutions.dev.usermanager.features.user.domain.usescases.DeleteUser
import com.oliversolutions.dev.usermanager.features.user.domain.usescases.UpdateUser
import kotlinx.coroutines.launch
import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.presentation.fragments.UserDetailFragmentDirections

class UserDetailViewModel(
    application: Application,
    private val updateUserUsecase: UpdateUser,
    private val deleteUserUseCase: DeleteUser) : BaseViewModel(application) {

    fun updateUser(user: User) {
        viewModelScope.launch {
            when (val result = updateUserUsecase.call(user)) {
                is Result.Success<*> -> {
                    showSnackBar.value = "User has been updated"
                    navigationCommand.value = NavigationCommand.To(UserDetailFragmentDirections.actionUserDetailFragmentToUsersFragment())
                }
                is Result.Error -> {
                    showSnackBar.value = "Unexpected error. Contact application owner."
                    Log.i(javaClass.simpleName, result.message)
                }
            }
        }
    }

    fun deleteUser(id: Int) {
        viewModelScope.launch {
            when (val result = deleteUserUseCase.call(id)) {
                is Result.Success<*> -> {
                    showSnackBar.value = "User has been deleted"
                    navigationCommand.value = NavigationCommand.To(UserDetailFragmentDirections.actionUserDetailFragmentToUsersFragment())
                }
                is Result.Error -> {
                    showSnackBar.value = "Unexpected error. Contact application owner."
                    Log.i(javaClass.simpleName, result.message)
                }
            }
        }
    }
}