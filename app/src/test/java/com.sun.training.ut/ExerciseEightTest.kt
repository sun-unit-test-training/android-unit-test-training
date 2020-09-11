package com.sun.training.ut

import com.sun.training.ut.data.Constants
import com.sun.training.ut.data.Constants.BADMINTON_FEE_1200
import com.sun.training.ut.data.Constants.BADMINTON_FEE_1400
import com.sun.training.ut.data.Constants.BADMINTON_FEE_1600
import com.sun.training.ut.data.Constants.BASE_BADMINTON_FEE
import com.sun.training.ut.data.Constants.DayOfWeek
import com.sun.training.ut.data.model.No8Member
import com.sun.training.ut.exercise.eight.calculateBadmintonFee
import com.sun.training.ut.exercise.eight.validateMemberAge
import org.junit.Assert.assertEquals
import org.junit.Test

class ExerciseEightTest {

    /**
     * Age: < 0
     * Gender: Any
     */
    @Test
    fun validateMemberAge_IncorrectAge_LesserThanMin_ReturnFalse() {
        val inputMember = No8Member(age = -1)
        assertEquals(false, validateMemberAge(inputMember))
    }

    /**
     * Age: > 120
     * Gender: Any
     */
    @Test
    fun validateMemberAge_IncorrectAge_GreaterThanMax_ReturnFalse() {
        val inputMember = No8Member(age = 150)
        assertEquals(false, validateMemberAge(inputMember))
    }

    /**
     * Age: 0 <= age <= 120
     * Gender: Any
     */
    @Test
    fun validateMemberAge_CorrectAge_ReturnTrue() {
        val inputMember = No8Member(age = 23)
        assertEquals(true, validateMemberAge(inputMember))
    }

    /**
     * Age: >= 13, <= 65
     * Gender: Any
     * DayOfWeek: Not Tuesday, not Friday
     */
    @Test
    fun calculateBadmintonFee_NormalMember_NotTuesday_NotFriday_Return1800() {
        val inputMember = No8Member(age = 25, gender = Constants.Gender.MALE)
        assertEquals(BASE_BADMINTON_FEE,
            calculateBadmintonFee(inputMember, DayOfWeek.WEDNESDAY))
    }

    /**
     * Age: >= 13, <= 65
     * Gender: Any
     * DayOfWeek: Tuesday
     */
    @Test
    fun calculateBadmintonFee_NormalMember_Tuesday_Return1200() {
        val inputMember = No8Member(age = 25, gender = Constants.Gender.MALE)
        assertEquals(
            BADMINTON_FEE_1200,
            calculateBadmintonFee(inputMember, DayOfWeek.TUESDAY))
    }

    /**
     * Age: >= 13, <= 65
     * Gender: Female
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_Female_NormalMember_Friday_Return1400() {
        val inputMember = No8Member(age = 25, gender = Constants.Gender.FEMALE)
        assertEquals(
            BADMINTON_FEE_1400,
            calculateBadmintonFee(inputMember, DayOfWeek.FRIDAY))
    }

    /**
     * Age: >= 13, <= 65
     * Gender: Male
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_Male_NormalMember_Friday_Return1800() {
        val inputMember = No8Member(age = 25, gender = Constants.Gender.MALE)
        assertEquals(
            BASE_BADMINTON_FEE,
            calculateBadmintonFee(inputMember, DayOfWeek.FRIDAY))
    }

    /**
     * Age: < 13
     * Gender: Any
     * DayOfWeek: Not Tuesday, not Friday
     */
    @Test
    fun calculateBadmintonFee_ChildrenMember_NotTuesday_NotFriday_Return900() {
        val inputMember = No8Member(age = 12, gender = Constants.Gender.MALE)
        assertEquals(
            BASE_BADMINTON_FEE / 2,
            calculateBadmintonFee(inputMember, DayOfWeek.WEDNESDAY))
    }

    /**
     * Age: < 13
     * Gender: Any
     * DayOfWeek: Tuesday
     */
    @Test
    fun calculateBadmintonFee_ChildrenMember_Tuesday_Return1200() {
        val inputMember = No8Member(age = 12, gender = Constants.Gender.MALE)
        assertEquals(BADMINTON_FEE_1200,
            calculateBadmintonFee(inputMember, DayOfWeek.TUESDAY))
    }

    /**
     * Age: < 13
     * Gender: Any
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_ChildrenMember_Friday_Return900() {
        val inputMember = No8Member(age = 12, gender = Constants.Gender.FEMALE)
        assertEquals(BASE_BADMINTON_FEE / 2,
            calculateBadmintonFee(inputMember, DayOfWeek.FRIDAY))
    }

    /**
     * Age: > 65
     * Gender: Any
     * DayOfWeek: Not Tuesday, not Friday
     */
    @Test
    fun calculateBadmintonFee_OldMember_NotTuesday_NotFriday_Return1600() {
        val inputMember = No8Member(age = 72, gender = Constants.Gender.MALE)
        assertEquals(
            BADMINTON_FEE_1600,
            calculateBadmintonFee(inputMember, DayOfWeek.WEDNESDAY))
    }

    /**
     * Age: > 65
     * Gender: Any
     * DayOfWeek: Tuesday
     */
    @Test
    fun calculateBadmintonFee_OldMember_Tuesday_Return1200() {
        val inputMember = No8Member(age = 72, gender = Constants.Gender.MALE)
        assertEquals(BADMINTON_FEE_1200,
            calculateBadmintonFee(inputMember, DayOfWeek.TUESDAY))
    }

    /**
     * Age: > 65
     * Gender: Female
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_Female_OldMember_Friday_Return1400() {
        val inputMember = No8Member(age = 72, gender = Constants.Gender.FEMALE)
        assertEquals(BADMINTON_FEE_1400,
            calculateBadmintonFee(inputMember, DayOfWeek.FRIDAY))
    }

    /**
     * Age: > 65
     * Gender: Male
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_Male_OldMember_Friday_Return1600() {
        val inputMember = No8Member(age = 72, gender = Constants.Gender.MALE)
        assertEquals(BADMINTON_FEE_1600,
            calculateBadmintonFee(inputMember, DayOfWeek.FRIDAY))
    }
}