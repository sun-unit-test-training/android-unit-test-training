package com.sun.training.ut.exercise.eight

import com.sun.training.ut.data.Constants
import com.sun.training.ut.data.Constants.DayOfWeek
import com.sun.training.ut.data.model.No8Member


fun validateMemberAge(member: No8Member): Boolean {
    return (member.age in 0..120)
}

fun calculateBadmintonFee(member: No8Member, dayOfWeek: DayOfWeek): Int {
    return when (dayOfWeek) {
        DayOfWeek.TUESDAY -> return Constants.BADMINTON_FEE_1200
        DayOfWeek.FRIDAY -> {
            when {
                (member.age in 0..13) -> Constants.BASE_BADMINTON_FEE / 2
                else -> {
                    if (member.gender == Constants.Gender.FEMALE)
                        Constants.BADMINTON_FEE_1400
                    else {
                        if (member.age in 66..120)
                            Constants.BADMINTON_FEE_1600
                        else
                            Constants.BASE_BADMINTON_FEE
                    }
                }
            }
        }
        else -> {
            when {
                member.age in 66..120 -> Constants.BADMINTON_FEE_1600
                member.age in 0..13 -> Constants.BASE_BADMINTON_FEE / 2
                else -> Constants.BASE_BADMINTON_FEE
            }
        }
    }
}