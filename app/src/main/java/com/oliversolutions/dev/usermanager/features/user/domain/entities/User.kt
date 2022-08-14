package com.oliversolutions.dev.usermanager.features.user.domain.entities

import android.os.Parcelable
import com.oliversolutions.dev.usermanager.features.user.data.models.UserModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class User(
    var name: String,
    var birthdate: @RawValue DateTime,
    val id: Int) : Parcelable

fun User.asDataModel() : UserModel {
    return UserModel(id = this.id, name = this.name, birthdate = this.birthdate.dateString)
}
