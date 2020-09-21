package com.sun.training.ut

import java.text.DecimalFormat

fun Int.formatNumberPrice(): String {
    val formatter = DecimalFormat("#,###")
    return formatter.format(this)
}