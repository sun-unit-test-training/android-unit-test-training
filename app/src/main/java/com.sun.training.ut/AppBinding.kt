package com.sun.training.ut

import android.widget.TextView
import android.widget.TimePicker
import androidx.databinding.BindingAdapter
import java.text.DecimalFormat

/**
 * @{AppBinding}
 * Created by nguyen.van.bac on 15,September,2020
 */

object AppBinding {
    @BindingAdapter("price")
    @JvmStatic
    fun price(tv: TextView, price: Int) {
        tv.text = price.formatNumberPrice()
    }
<<<<<<< HEAD
}
=======

    @BindingAdapter("is24Hour")
    @JvmStatic
    fun is24Hour(timePicker: TimePicker, is24Hour: Boolean) {
        timePicker.setIs24HourView(true)
    }
}
>>>>>>> Refs exercise two: Calculate fee pay ATM
