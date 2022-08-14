package com.oliversolutions.dev.usermanager.features.user.domain.entities

import java.text.SimpleDateFormat
import java.util.*

class DateTime(val dateString: String) {

    companion object {
        fun getDateInIso8601(date: String, time: String) : String {
            return SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm").format(SimpleDateFormat("yyyy-MM-ddHH:mm").parse(date + time))
        }
    }

    fun getDate() : String {
        val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH)
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        return formatter.format(parser.parse(dateString)!!)
    }

    fun getTime() : String {
        val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH)
        val formatter = SimpleDateFormat("HH:mm", Locale.ENGLISH)
        return formatter.format(parser.parse(dateString)!!)
    }
}