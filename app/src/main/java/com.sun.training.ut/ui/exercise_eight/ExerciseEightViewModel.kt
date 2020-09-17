package com.sun.training.ut.ui.exercise_eight

import androidx.lifecycle.MutableLiveData
import com.sun.training.ut.data.Constants
import com.sun.training.ut.data.model.No8Member
import com.sun.training.ut.ui.base.BaseViewModel

class ExerciseEightViewModel : BaseViewModel() {

    var member: No8Member? = null
        set(value) {
            field = value
            calculateBadmintonFee()
        }

    var dayOfWeek: Constants.DayOfWeek? = null
        set(value) {
            field = value
            calculateBadmintonFee()
        }

    var fee = MutableLiveData<String>()

    fun validateMemberAge(member: No8Member?): Boolean {
        return (member?.age in Constants.BADMINTON_MIN_AGE..Constants.BADMINTON_MAX_AGE)
    }

    fun calculateBadmintonFee(member: No8Member?, dayOfWeek: Constants.DayOfWeek?): Int {
        return when (dayOfWeek) {
            Constants.DayOfWeek.TUESDAY -> return Constants.BADMINTON_FEE_1200
            Constants.DayOfWeek.FRIDAY -> {
                when {
                    (member?.age in 0..13) -> Constants.BASE_BADMINTON_FEE / 2
                    else -> {
                        if (member?.gender == Constants.Gender.FEMALE)
                            Constants.BADMINTON_FEE_1400
                        else {
                            if (member?.age in 66..120)
                                Constants.BADMINTON_FEE_1600
                            else
                                Constants.BASE_BADMINTON_FEE
                        }
                    }
                }
            }
            else -> {
                when {
                    member?.age in 66..120 -> Constants.BADMINTON_FEE_1600
                    member?.age in 0..13 -> Constants.BASE_BADMINTON_FEE / 2
                    else -> Constants.BASE_BADMINTON_FEE
                }
            }
        }
    }

    fun calculateBadmintonFee() {
        if (!validateMemberAge(member = member) || member == null || dayOfWeek == null)
            return

        fee.value = calculateBadmintonFee(member = member, dayOfWeek = dayOfWeek).toString()
    }
}