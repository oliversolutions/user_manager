package com.oliversolutions.dev.usermanager.features.user.presentation.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.oliversolutions.dev.usermanager.R
import com.oliversolutions.dev.usermanager.core.base.BaseViewModel
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User
import kotlinx.coroutines.launch
import com.oliversolutions.dev.usermanager.core.error.Result
import com.oliversolutions.dev.usermanager.features.user.domain.usescases.*

class UsersViewModel(val app: Application, private val usecase: GetUsers) : BaseViewModel(app) {

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
                    showSnackBar.value = app.getString(R.string.unexpected_error)
                    Log.i(UsersViewModel::class.java.simpleName, result.message)
                }
            }
        }
    }

    private fun invalidateShowNoData() {
        showNoData.value = users.value == null || users.value!!.isEmpty()
    }
}