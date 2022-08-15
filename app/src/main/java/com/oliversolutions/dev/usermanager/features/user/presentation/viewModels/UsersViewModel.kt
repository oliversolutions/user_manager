package com.oliversolutions.dev.usermanager.features.user.presentation.viewModels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oliversolutions.dev.usermanager.core.base.BaseViewModel
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User
import kotlinx.coroutines.launch
import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.domain.usescases.*

class UsersViewModel(application: Application, private val usecase: GetUsers) : BaseViewModel(application) {

    val users = MutableLiveData<List<User>>()

    init {
        setUsers()
    }

    fun setUsers() {
        showLoading.value = true
        viewModelScope.launch {
            when (val result = usecase.call()) {
                is Result.Success<*> -> {
                    val data = result.data as List<User>
                    users.value = data
                    showLoading.value = false
                    invalidateShowNoData()
                }
                is Result.Error -> {
                    showLoading.value = false
                    showSnackBar.value = result.message
                }
            }
        }
    }

    private fun invalidateShowNoData() {
        showNoData.value = users.value == null || users.value!!.isEmpty()
    }
}