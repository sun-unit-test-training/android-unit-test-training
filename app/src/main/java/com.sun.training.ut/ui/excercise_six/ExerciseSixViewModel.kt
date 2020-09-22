package com.sun.training.ut.ui.excercise_six

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import com.sun.training.ut.ui.base.BaseViewModel

const val FIRST_MONEY_POINT = 2000
const val FIRST_FREE_TIME = 60
const val SECOND_MONEY_POINT = 5000
const val SECOND_FREE_TIME = 120
const val WATCH_MOVIE_FREE_TIME = 180

/**
◎Exercise
Tại trung tâm mua sắm "Tây Hồ", hiện đang cung cấp dịch vụ trông xe miễn phí trong một khoảng thời gian nhất định,
tuỳ thuộc vào tổng số tiền mua sắm và việc khách hàng có xem phim tại trung tâm không.
Các điều kiện được ghi ở bên dưới:
---
●Điều kiện tiên quyết：
①：Trường hợp tổng số tiền mua sắm từ 2,000円/yên trở lên, miễn phí phí gửi xe trong 60 phút.
②：Trường hợp tổng số tiền mua sắm từ 5,000円/yên trở lên, miễn phí gửi xe trong 120 phút.
③：Nếu khách hàng có xem phim, miễn phí gửi xe thêm 180 phút, cộng bổ sung vào cùng với tổng số tiền mua sắm.
 */
class ExerciseSixViewModel : BaseViewModel() {

    private var isWatchMovie = false

    val totalPurchased = MutableLiveData<String>()
    val freeParkingInMinute = MutableLiveData<String>()

    fun calculateMinute() {
        freeParkingInMinute.value = calculateFreeParkingMinute(
            totalMoney = totalPurchased.value?.toIntOrNull() ?: 0,
            watchMovie = isWatchMovie
        ).toString()
    }

    fun onWatchMovieChecked(isChecked: Boolean) {
        isWatchMovie = isChecked
    }

    /**
     * calculate free parking minute from total purchased money and watch movie or not
     * @param totalMoney total purchased money
     * @param watchMovie flag detect watch movie or not
     * @return free parking time in minute
     */
    @VisibleForTesting
    fun calculateFreeParkingMinute(totalMoney: Int, watchMovie: Boolean): Int {
        val freeTimeByTotalMoney = when {
            totalMoney < FIRST_MONEY_POINT -> 0
            totalMoney in FIRST_MONEY_POINT until SECOND_MONEY_POINT -> FIRST_FREE_TIME
            // SECOND_MONEY_POINT <= totalMoney
            else -> SECOND_FREE_TIME
        }
        return if (watchMovie) freeTimeByTotalMoney + WATCH_MOVIE_FREE_TIME else freeTimeByTotalMoney
    }

}