package com.oliversolutions.dev.usermanager.features.user.data.models

import android.os.Parcelable
import com.oliversolutions.dev.usermanager.features.user.domain.entities.DateTime
import com.oliversolutions.dev.usermanager.features.user.domain.entities.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val id: Int,
    val name: String,
    val birthdate: String) : Parcelable

fun UserModel.asDomainModel(): User {
    return User(id = this.id, name = this.name, birthdate = DateTime(this.birthdate))
}

