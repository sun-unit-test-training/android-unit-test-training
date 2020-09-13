package com.sun.training.ut.exercise.three

import com.sun.training.ut.data.model.Shopping

fun calculateDiscount(shopping: Shopping): Int {
    var discount = 0

    discount += if (shopping.isTie && shopping.isWhiteShirt) {
        if (shopping.isSevenItems) 12 else 5
    } else if (shopping.isSevenItems) {
        7
    } else {
        0
    }

    return discount
}