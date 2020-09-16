package com.sun.training.ut

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.DecimalFormat

/**
 * @{AppBinding}
 * Created by nguyen.van.bac on 15,September,2020
 */

object AppBinding  {
    @BindingAdapter("price")
    @JvmStatic
    fun price(tv: TextView, price: Int) {
        val formatter = DecimalFormat("#,###")
        tv.text = formatter.format(price)
    }
}