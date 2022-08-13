package com.oliversolutions.dev.usermanager.features.user.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val name: String,
    val birthdate: String,
    val id: Int) : Parcelable
