package com.sun.training.ut.ui.exercise_five

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import com.sun.training.ut.data.Constant
import com.sun.training.ut.data.Constant.DEFAULT_PRICE
import com.sun.training.ut.data.model.Pizza
import com.sun.training.ut.ui.base.BaseViewModel

class ExerciseFiveViewModel : BaseViewModel() {
    private var isDelivery: Boolean = false
    private var isVoucher: Boolean = false

    var totalPrice = 0
    var discountLiveData: MutableLiveData<String> = MutableLiveData()

    fun calculateCouponWithPizza() {
        val typeDelivery =
            if (isDelivery) Constant.TypeDelivery.DELIVERY else Constant.TypeDelivery.RECEIVE_AT_STORE
        val discount = calculateCouponWithPizza(
            Pizza(
                bill = totalPrice,
                typeDelivery = typeDelivery,
                isCoupon = isVoucher
            )
        )
        discountLiveData.postValue(discount)
    }

    @VisibleForTesting
    fun calculateCouponWithPizza(pizza: Pizza): String {
        var coupon = ""

        if (pizza.bill > DEFAULT_PRICE) {
            coupon += Constant.Coupon.POTATO_PROMOTION.coupon
        }

        coupon += if (pizza.typeDelivery == Constant.TypeDelivery.DELIVERY) {
            if (pizza.isCoupon) {
                Constant.Coupon.OFF_20.coupon
            } else {
                Constant.Coupon.REGULAR_FEE.coupon
            }
        } else {
            Constant.Coupon.PIZZA_SECOND_FREE.coupon
        }

        return coupon
    }

    fun onChangedDelivery(isChecked: Boolean) {
        isDelivery = isChecked
    }

    fun onChangedVoucher(isChecked: Boolean) {
        isVoucher = isChecked
    }

}