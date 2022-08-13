package com.oliversolutions.dev.usermanager.features.user.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Int,
    val name: String,
    val birthdate: String) : Parcelable
