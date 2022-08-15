package com.oliversolutions.dev.usermanager.features.user.presentation.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.oliversolutions.dev.usermanager.R
import com.oliversolutions.dev.usermanager.core.base.BaseViewModel
import com.oliversolutions.dev.usermanager.core.base.NavigationCommand
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User
import com.oliversolutions.dev.usermanager.features.user.domain.usescases.CreateUser
import kotlinx.coroutines.launch
import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.presentation.fragments.NewUserFragmentDirections

class NewUserViewModel(
    val app: Application,
    private val usecase: CreateUser
) : BaseViewModel(app) {

    fun createUser(user: User) {
        viewModelScope.launch {
            when (val result = usecase.call(user)) {
                is Result.Success<*> -> {
                    showSnackBar.value = app.getString(R.string.user_has_been_created)
                    navigationCommand.value = NavigationCommand.To(NewUserFragmentDirections.actionNewUserFragmentToUsersFragment())
                }
                is Result.Error -> {
                    showSnackBar.value = app.getString(R.string.unexpected_error)
                    Log.i(NewUserViewModel::class.java.simpleName, result.message)
                }
            }
        }
    }
}