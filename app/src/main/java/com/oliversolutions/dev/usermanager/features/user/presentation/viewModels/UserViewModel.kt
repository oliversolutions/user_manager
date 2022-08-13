package com.oliversolutions.dev.usermanager.features.user.presentation.viewModels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oliversolutions.dev.usermanager.core.base.BaseViewModel
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User
import kotlinx.coroutines.launch
import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.domain.usescases.*

class UserViewModel(application: Application, private val usecase: GetUsers) : BaseViewModel(application) {

    val users = MutableLiveData<List<User>>()
    val user = MutableLiveData<User>()

    init {
        setUsers()
    }

    /*fun getUser(id: String) {
        showLoading.value = true
        viewModelScope.launch {
            val result = usecase2.call(id)
            when (result) {
                is Result.Success<*> -> {
                    val data = result.data as User
                    user.value = data
                }
                is Result.Error ->
                    showSnackBar.value = result.message
            }
        }
    }

    fun deleteUser(id: String) {
        showLoading.value = true
        viewModelScope.launch {
            val result = usecase3.call(id)
            when (result) {
                is Result.Success<*> -> {
                    showSnackBar.value = "User has been deleted"
                }
                is Result.Error ->
                    showSnackBar.value = result.message
            }
        }
    }

    fun updateUser(user: User) {
        showLoading.value = true
        viewModelScope.launch {
            val result = usecase4.call(user)
            when (result) {
                is Result.Success<*> -> {
                    showSnackBar.value = "User has been updated"
                }
                is Result.Error ->
                    showSnackBar.value = result.message
            }
        }
    }

    fun createUser(user: User) {
        showLoading.value = true
        viewModelScope.launch {
            val result = usecase5.call(user)
            when (result) {
                is Result.Success<*> -> {
                    showSnackBar.value = "User has been created"
                }
                is Result.Error ->
                    showSnackBar.value = result.message
            }
        }
    }*/

    private fun setUsers() {
        showLoading.value = true
        viewModelScope.launch {
            val result = usecase.call()
            when (result) {
                is Result.Success<*> -> {
                    showLoading.postValue(false)
                    val data = result.data as List<User>
                    users.value = data
                }
                is Result.Error ->
                    showSnackBar.value = result.message
            }
        }
    }

}