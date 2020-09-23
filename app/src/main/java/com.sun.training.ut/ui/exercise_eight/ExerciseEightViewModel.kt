package com.sun.training.ut.ui.exercise_eight

import androidx.lifecycle.MutableLiveData
import com.sun.training.ut.data.Constant
import com.sun.training.ut.data.model.No8Member
import com.sun.training.ut.ui.base.BaseViewModel

class ExerciseEightViewModel : BaseViewModel() {

    var member = object : MutableLiveData<No8Member>() {
        override fun setValue(value: No8Member?) {
            super.setValue(value)
            calculateBadmintonFee()
        }
    }

    var dayOfWeek: Constant.DayOfWeek? = null
        set(value) {
            field = value
            calculateBadmintonFee()
        }

    var fee = MutableLiveData<String>()

    fun validateMemberAge(member: No8Member?): Boolean {
        return (member?.age in Constant.BADMINTON_MIN_AGE..Constant.BADMINTON_MAX_AGE)
    }

    fun calculateBadmintonFee(member: No8Member?, dayOfWeek: Constant.DayOfWeek?): Int {
        return when (dayOfWeek) {
            Constant.DayOfWeek.TUESDAY -> return Constant.BADMINTON_FEE_1200
            Constant.DayOfWeek.FRIDAY -> {
                when {
                    (member?.age in 0..13) -> Constant.BASE_BADMINTON_FEE / 2
                    else -> {
                        if (member?.gender == Constant.Gender.FEMALE)
                            Constant.BADMINTON_FEE_1400
                        else {
                            if (member?.age in 66..120)
                                Constant.BADMINTON_FEE_1600
                            else
                                Constant.BASE_BADMINTON_FEE
                        }
                    }
                }
            }
            else -> {
                when {
                    member?.age in 66..120 -> Constant.BADMINTON_FEE_1600
                    member?.age in 0..13 -> Constant.BASE_BADMINTON_FEE / 2
                    else -> Constant.BASE_BADMINTON_FEE
                }
            }
        }
    }

    fun calculateBadmintonFee() {
        if (!validateMemberAge(member = member.value) || member.value == null || dayOfWeek == null)
            return

        fee.value = calculateBadmintonFee(member = member.value, dayOfWeek = dayOfWeek).toString()
    }

    fun genderChangedMale(isChecked: Boolean) {
        if (isChecked)
            member.value = No8Member(
                age = member.value?.age ?: 0, gender = Constant.Gender.MALE
            )
    }

    fun genderChangedFemale(isChecked: Boolean) {
        if (isChecked)
            member.value = No8Member(
                age = member.value?.age ?: 0, gender = Constant.Gender.FEMALE
            )
    }

    fun ageChanged(newVal: Int) {
        member.value = No8Member(
            age = newVal, gender = member.value?.gender ?: Constant.Gender.MALE
        )
    }
}