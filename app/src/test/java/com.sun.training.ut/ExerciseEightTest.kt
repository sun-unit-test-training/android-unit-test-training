package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.Constant
import com.sun.training.ut.data.Constant.BADMINTON_FEE_1200
import com.sun.training.ut.data.Constant.BADMINTON_FEE_1400
import com.sun.training.ut.data.Constant.BADMINTON_FEE_1600
import com.sun.training.ut.data.Constant.BASE_BADMINTON_FEE
import com.sun.training.ut.data.Constant.DayOfWeek
import com.sun.training.ut.data.model.No8Member
import com.sun.training.ut.ui.exercise_eight.ExerciseEightViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExerciseEightTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseEightViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseEightViewModel()
    }

    /**
     * Age: < 0
     * Gender: Any
     */
    @Test
    fun validateMemberAge_IncorrectAge_LesserThanMin_ReturnFalse() {
        val inputMember = No8Member(age = -1)
        assertEquals(false, viewModel.validateMemberAge(inputMember))
    }

    /**
     * Age: > 120
     * Gender: Any
     */
    @Test
    fun validateMemberAge_IncorrectAge_GreaterThanMax_ReturnFalse() {
        val inputMember = No8Member(age = 150)
        assertEquals(false, viewModel.validateMemberAge(inputMember))
    }

    /**
     * Age: = 121
     * Gender: Any
     */
    @Test
    fun validateMemberAge_IncorrectAge_GreaterThanMax_Age121_ReturnFalse() {
        val inputMember = No8Member(age = 121)
        assertEquals(false, viewModel.validateMemberAge(inputMember))
    }

    /**
     * Age: 0 <= age <= 120
     * Gender: Any
     */
    @Test
    fun validateMemberAge_CorrectAge_ReturnTrue() {
        val inputMember = No8Member(age = 23)
        assertEquals(true, viewModel.validateMemberAge(inputMember))
    }

    /**
     * Age: >= 13, <= 65
     * Gender: Any
     * DayOfWeek: Not Tuesday, not Friday
     */
    @Test
    fun calculateBadmintonFee_NormalMember_NotTuesday_NotFriday_Return1800() {
        val inputMember = No8Member(age = 25, gender = Constant.Gender.MALE)
        assertEquals(BASE_BADMINTON_FEE,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.WEDNESDAY))
    }

    /**
     * Age: = 14
     * Gender: Any
     * DayOfWeek: Not Tuesday, not Friday
     */
    @Test
    fun calculateBadmintonFee_NormalMember_NotTuesday_NotFriday_Age14_Return1800() {
        val inputMember = No8Member(age = 14, gender = Constant.Gender.MALE)
        assertEquals(BASE_BADMINTON_FEE,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.WEDNESDAY))
    }

    /**
     * Age: = 65
     * Gender: Any
     * DayOfWeek: Not Tuesday, not Friday
     */
    @Test
    fun calculateBadmintonFee_NormalMember_NotTuesday_NotFriday_Age65_Return1800() {
        val inputMember = No8Member(age = 65, gender = Constant.Gender.MALE)
        assertEquals(BASE_BADMINTON_FEE,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.WEDNESDAY))
    }

    /**
     * Age: >= 13, <= 65
     * Gender: Any
     * DayOfWeek: Tuesday
     */
    @Test
    fun calculateBadmintonFee_NormalMember_Tuesday_Return1200() {
        val inputMember = No8Member(age = 25, gender = Constant.Gender.MALE)
        assertEquals(
            BADMINTON_FEE_1200,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.TUESDAY))
    }

    /**
     * Age: >= 13, <= 65
     * Gender: Female
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_Female_NormalMember_Friday_Return1400() {
        val inputMember = No8Member(age = 25, gender = Constant.Gender.FEMALE)
        assertEquals(
            BADMINTON_FEE_1400,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.FRIDAY))
    }

    /**
     * Age: = 14
     * Gender: Female
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_Female_NormalMember_Friday_Age14_Return1400() {
        val inputMember = No8Member(age = 14, gender = Constant.Gender.FEMALE)
        assertEquals(
            BADMINTON_FEE_1400,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.FRIDAY))
    }

    /**
     * Age: = 65
     * Gender: Female
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_Female_NormalMember_Friday_Age65_Return1400() {
        val inputMember = No8Member(age = 65, gender = Constant.Gender.FEMALE)
        assertEquals(
            BADMINTON_FEE_1400,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.FRIDAY))
    }

    /**
     * Age: >= 13, <= 65
     * Gender: Male
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_Male_NormalMember_Friday_Return1800() {
        val inputMember = No8Member(age = 25, gender = Constant.Gender.MALE)
        assertEquals(
            BASE_BADMINTON_FEE,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.FRIDAY))
    }

    /**
     * Age: < 13
     * Gender: Any
     * DayOfWeek: Not Tuesday, not Friday
     */
    @Test
    fun calculateBadmintonFee_ChildrenMember_NotTuesday_NotFriday_Return900() {
        val inputMember = No8Member(age = 8, gender = Constant.Gender.MALE)
        assertEquals(
            BASE_BADMINTON_FEE / 2,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.WEDNESDAY))
    }

    /**
     * Age: = 13
     * Gender: Any
     * DayOfWeek: Not Tuesday, not Friday
     */
    @Test
    fun calculateBadmintonFee_ChildrenMember_NotTuesday_NotFriday_Age13_Return900() {
        val inputMember = No8Member(age = 13, gender = Constant.Gender.MALE)
        assertEquals(
            BASE_BADMINTON_FEE / 2,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.WEDNESDAY))
    }

    /**
     * Age: < 13
     * Gender: Any
     * DayOfWeek: Tuesday
     */
    @Test
    fun calculateBadmintonFee_ChildrenMember_Tuesday_Return1200() {
        val inputMember = No8Member(age = 8, gender = Constant.Gender.MALE)
        assertEquals(BADMINTON_FEE_1200,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.TUESDAY))
    }

    /**
     * Age: = 13
     * Gender: Any
     * DayOfWeek: Tuesday
     */
    @Test
    fun calculateBadmintonFee_ChildrenMember_Tuesday_Age13_Return1200() {
        val inputMember = No8Member(age = 13, gender = Constant.Gender.MALE)
        assertEquals(BADMINTON_FEE_1200,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.TUESDAY))
    }

    /**
     * Age: < 13
     * Gender: Any
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_ChildrenMember_Friday_Return900() {
        val inputMember = No8Member(age = 8, gender = Constant.Gender.FEMALE)
        assertEquals(BASE_BADMINTON_FEE / 2,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.FRIDAY))
    }

    /**
     * Age: = 13
     * Gender: Any
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_ChildrenMember_Friday_Age13_Return900() {
        val inputMember = No8Member(age = 13, gender = Constant.Gender.FEMALE)
        assertEquals(BASE_BADMINTON_FEE / 2,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.FRIDAY))
    }

    /**
     * Age: > 65
     * Gender: Any
     * DayOfWeek: Not Tuesday, not Friday
     */
    @Test
    fun calculateBadmintonFee_OldMember_NotTuesday_NotFriday_Return1600() {
        val inputMember = No8Member(age = 72, gender = Constant.Gender.MALE)
        assertEquals(
            BADMINTON_FEE_1600,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.WEDNESDAY))
    }

    /**
     * Age: = 66
     * Gender: Any
     * DayOfWeek: Not Tuesday, not Friday
     */
    @Test
    fun calculateBadmintonFee_OldMember_NotTuesday_NotFriday_Age66_Return1600() {
        val inputMember = No8Member(age = 66, gender = Constant.Gender.MALE)
        assertEquals(
            BADMINTON_FEE_1600,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.WEDNESDAY))
    }

    /**
     * Age: > 65
     * Gender: Any
     * DayOfWeek: Tuesday
     */
    @Test
    fun calculateBadmintonFee_OldMember_Tuesday_Return1200() {
        val inputMember = No8Member(age = 72, gender = Constant.Gender.MALE)
        assertEquals(BADMINTON_FEE_1200,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.TUESDAY))
    }

    /**
     * Age: = 66
     * Gender: Any
     * DayOfWeek: Tuesday
     */
    @Test
    fun calculateBadmintonFee_OldMember_Tuesday_Age66_Return1200() {
        val inputMember = No8Member(age = 66, gender = Constant.Gender.MALE)
        assertEquals(BADMINTON_FEE_1200,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.TUESDAY))
    }

    /**
     * Age: > 65
     * Gender: Female
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_Female_OldMember_Friday_Return1400() {
        val inputMember = No8Member(age = 72, gender = Constant.Gender.FEMALE)
        assertEquals(BADMINTON_FEE_1400,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.FRIDAY))
    }

    /**
     * Age: = 66
     * Gender: Female
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_Female_OldMember_Friday_Age66_Return1400() {
        val inputMember = No8Member(age = 66, gender = Constant.Gender.FEMALE)
        assertEquals(BADMINTON_FEE_1400,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.FRIDAY))
    }

    /**
     * Age: > 65
     * Gender: Male
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_Male_OldMember_Friday_Return1600() {
        val inputMember = No8Member(age = 72, gender = Constant.Gender.MALE)
        assertEquals(BADMINTON_FEE_1600,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.FRIDAY))
    }

    /**
     * Age: = 66
     * Gender: Male
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_Male_OldMember_Friday_Age66_Return1600() {
        val inputMember = No8Member(age = 66, gender = Constant.Gender.MALE)
        assertEquals(BADMINTON_FEE_1600,
            viewModel.calculateBadmintonFee(inputMember, DayOfWeek.FRIDAY))
    }
}