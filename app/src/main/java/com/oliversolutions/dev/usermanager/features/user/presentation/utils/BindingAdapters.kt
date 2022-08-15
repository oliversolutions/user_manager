package com.oliversolutions.dev.usermanager.features.user.presentation.utils

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.oliversolutions.dev.usermanager.core.utils.fadeIn
import com.oliversolutions.dev.usermanager.core.utils.fadeOut
import com.oliversolutions.dev.usermanager.features.user.domain.entities.DateTime
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("idToString")
fun bindIdValue(textView: TextView, value: Int) {
    textView.text = value.toString()
}

@BindingAdapter("printDate")
fun bindDate(textView: TextView, value: DateTime) {
    textView.text = value.getDate()
}

@BindingAdapter("printTime")
fun bindTime(textView: TextView, value: DateTime) {
    textView.text = value.getTime()
}

@BindingAdapter("currentDate")
fun bindCurrentDate(textView: TextView, value: String?) {
    val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
    textView.text = dateFormat.format(Calendar.getInstance().time)
}

@BindingAdapter("currentTime")
fun bindCurrentTime(textView: TextView, value: String?) {
    val dateFormat: DateFormat = SimpleDateFormat("HH:mm")
    textView.text = dateFormat.format(Calendar.getInstance().time)
}

@BindingAdapter("beautyDate")
fun bindBeautyDate(textView: TextView, value: DateTime) {
    textView.text = value.getBeautyDate()
}

@BindingAdapter("android:fadeVisible")
fun setFadeVisible(view: View, visible: Boolean? = true) {
    if (view.tag == null) {
        view.tag = true
        view.visibility = if (visible == true) View.VISIBLE else View.GONE
    } else {
        view.animate().cancel()
        if (visible == true) {
            if (view.visibility == View.GONE)
                view.fadeIn()
        } else {
            if (view.visibility == View.VISIBLE)
                view.fadeOut()
        }
    }
}

