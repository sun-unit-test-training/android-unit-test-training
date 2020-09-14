package com.sun.training.ut.ui.exercise_one

import androidx.lifecycle.MutableLiveData
import com.sun.training.ut.data.Constant
import com.sun.training.ut.data.model.Beer
import com.sun.training.ut.ui.base.BaseViewModel

class ExerciseOneViewModel : BaseViewModel() {
    private var isTimeCoupon: Boolean = false
    private var isVoucher: Boolean = false

    var numberBeer = 0
    var priceLiveData: MutableLiveData<Int> = MutableLiveData()

    fun calculatePrice() {
        calculatePrice(Beer(isVoucher, isTimeCoupon, numberBeer))
    }

    fun calculatePrice(beer: Beer) : Int {
        val priceOfBeer = when {
            beer.isVoucher -> {
                Constant.VOUCHER_PRICE
            }
            beer.isTimeCoupon -> {
                Constant.TIME_PRICE
            }
            else -> Constant.REGULAR_PRICE
        }
        val total = priceOfBeer * beer.numberBeer
        priceLiveData.postValue(total)
        return total
    }

    fun onTimeCouponChecked(isChecked: Boolean) {
        isTimeCoupon = isChecked
    }

    fun onVoucherChecked(isChecked: Boolean) {
        isVoucher = isChecked
    }

}