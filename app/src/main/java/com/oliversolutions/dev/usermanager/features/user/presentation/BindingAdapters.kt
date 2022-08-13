package com.oliversolutions.dev.usermanager.features.user.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.oliversolutions.dev.usermanager.core.utils.fadeIn
import com.oliversolutions.dev.usermanager.core.utils.fadeOut

@BindingAdapter("idToString")
fun bindIdValue(textView: TextView, value: Int) {
    textView.text = value.toString()
}

/**
 * Use this binding adapter to show and hide the views using boolean variables
 */
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

