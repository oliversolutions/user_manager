package com.oliversolutions.dev.usermanager.features.user.presentation.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.oliversolutions.dev.usermanager.core.base.BaseViewModel
import com.oliversolutions.dev.usermanager.core.base.NavigationCommand
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User
import com.oliversolutions.dev.usermanager.features.user.domain.usescases.CreateUser
import kotlinx.coroutines.launch
import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.presentation.fragments.NewUserFragmentDirections

class NewUserViewModel(
    application: Application,
    private val usecase: CreateUser
) : BaseViewModel(application) {

    fun createUser(user: User) {
        showLoading.value = true
        viewModelScope.launch {
            when (val result = usecase.call(user)) {
                is Result.Success<*> -> {
                    showSnackBar.value = "User has been created"
                    navigationCommand.value = NavigationCommand.To(NewUserFragmentDirections.actionNewUserFragmentToUsersFragment())
                }
                is Result.Error -> {
                    showSnackBar.value = "Unexpected error. Contact application owner."
                    Log.i(javaClass.simpleName, result.message)
                }
            }
        }
    }
}