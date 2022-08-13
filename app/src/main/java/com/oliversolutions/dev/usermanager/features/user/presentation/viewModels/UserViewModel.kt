package com.oliversolutions.dev.usermanager.features.user.presentation.viewModels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oliversolutions.dev.usermanager.core.base.BaseViewModel
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User
import com.oliversolutions.dev.usermanager.features.user.domain.usescases.GetUsers
import kotlinx.coroutines.launch
import com.oliversolutions.dev.usermanager.core.error.Result

class UserViewModel(application: Application, private val usecase: GetUsers) : BaseViewModel(application) {
    val users = MutableLiveData<List<User>>()

    init {
        getUsers()
    }

    private fun getUsers() {
        showLoading.value = true
        viewModelScope.launch {
            val result = usecase.call()
            when (result) {
                is Result.Success<*> -> {
                    val data = result.data as List<User>
                    users.value = data
                }
                is Result.Error ->
                    showSnackBar.value = result.message
            }
        }
    }

}