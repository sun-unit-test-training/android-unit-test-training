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
import kotlin.jvm.Throws

@RunWith(MockitoJUnitRunner::class)
class ExerciseEightViewModelTest {

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
    fun validateMemberAge_MemberNull_LesserThanMin_ReturnFalse() {
        assertEquals(false, viewModel.validateMemberAge(null))
    }

    /**
     * Age: < 0
     * Gender: Any
     */
    @Test
    fun validateMemberAge_IncorrectAge_LesserThanMin_ReturnFalse() {
        viewModel.ageChanged(-1)
        assertEquals(false, viewModel.validateMemberAge(viewModel.member.value))
    }

    /**
     * Age: > 120
     * Gender: Any
     */
    @Test
    fun validateMemberAge_IncorrectAge_GreaterThanMax_ReturnFalse() {
        viewModel.ageChanged(150)
        assertEquals(false, viewModel.validateMemberAge(viewModel.member.value))
    }

    /**
     * Age: = 121
     * Gender: Any
     */
    @Test
    fun validateMemberAge_IncorrectAge_GreaterThanMax_Age121_ReturnFalse() {
        viewModel.ageChanged(121)
        assertEquals(false, viewModel.validateMemberAge(viewModel.member.value))
    }

    /**
     * Age: 0 <= age <= 120
     * Gender: Any
     */
    @Test
    fun validateMemberAge_CorrectAge_ReturnTrue() {
        viewModel.member.value = No8Member(19, Constant.Gender.MALE)
        viewModel.ageChanged(23)
        assertEquals(true, viewModel.validateMemberAge(viewModel.member.value))
    }

    @Test
    fun genderChangedMale_CheckTrue_ReturnMale() {
        viewModel.genderChangedMale(true)
        assertEquals(Constant.Gender.MALE, viewModel.member.value?.gender)
    }

    @Test
    fun genderChangedMale_CheckFalse_ReturnMemberNull() {
        viewModel.genderChangedMale(false)
        assertEquals(null, viewModel.member.value)
    }

    @Test
    fun genderChangedFemale_CheckTrue_ReturnFemale() {
        viewModel.genderChangedFemale(true)
        assertEquals(Constant.Gender.FEMALE, viewModel.member.value?.gender)
    }

    @Test
    fun genderChangedFemale_CheckFalse_ReturnMemberNull() {
        viewModel.genderChangedFemale(false)
        assertEquals(null, viewModel.member.value)
    }

    @Test
    fun calculateBadmintonFee_DayOfWeekNull_ReturnNull() {
        viewModel.dayOfWeek = null
        viewModel.calculateBadmintonFee()
        assertEquals(null, viewModel.fee.value)
    }

    @Test
    fun calculateBadmintonFee_MemberNull_ReturnNull() {
        viewModel.member.value = null
        viewModel.dayOfWeek = DayOfWeek.WEDNESDAY
        viewModel.calculateBadmintonFee()
        assertEquals(null, viewModel.fee.value)
    }

    @Test
    fun calculateBadmintonFee_InvalidAge_ReturnNull() {
        viewModel.ageChanged(-1)
        viewModel.dayOfWeek = DayOfWeek.WEDNESDAY
        viewModel.calculateBadmintonFee()
        assertEquals(null, viewModel.fee.value)
    }


    /**
     * Age: >= 13, <= 65
     * Gender: Any
     * DayOfWeek: Not Tuesday, not Friday
     */
    @Test
    fun calculateBadmintonFee_MemberWithGenderNull_NotTuesday_NotFriday_Return1800() {
        viewModel.ageChanged(25)
        viewModel.genderChangedMale(true)
        viewModel.dayOfWeek = DayOfWeek.WEDNESDAY
        viewModel.calculateBadmintonFee()
        assertEquals(DayOfWeek.WEDNESDAY, viewModel.dayOfWeek)
        assertEquals(BASE_BADMINTON_FEE, viewModel.fee.value)
    }

    /**
     * Age: >= 13, <= 65
     * Gender: Any
     * DayOfWeek: Not Tuesday, not Friday
     */
    @Test
    fun calculateBadmintonFee_MemberNull_NotTuesday_NotFriday_Return1800() {
        viewModel.member.value = null
        viewModel.ageChanged(25)
        viewModel.genderChangedMale(true)
        viewModel.dayOfWeek = DayOfWeek.WEDNESDAY
        viewModel.calculateBadmintonFee()
        assertEquals(DayOfWeek.WEDNESDAY, viewModel.dayOfWeek)
        assertEquals(BASE_BADMINTON_FEE, viewModel.fee.value)
    }

    /**
     * Age: >= 13, <= 65
     * Gender: Any
     * DayOfWeek: Not Tuesday, not Friday
     */
    @Test
    fun calculateBadmintonFee_NormalMember_NotTuesday_NotFriday_Return1800() {
        viewModel.ageChanged(25)
        viewModel.genderChangedMale(true)
        viewModel.dayOfWeek = DayOfWeek.WEDNESDAY
        viewModel.calculateBadmintonFee()
        assertEquals(DayOfWeek.WEDNESDAY, viewModel.dayOfWeek)
        assertEquals(BASE_BADMINTON_FEE, viewModel.fee.value)
    }

    /**
     * Age: = 14
     * Gender: Any
     * DayOfWeek: Not Tuesday, not Friday
     */
    @Test
    fun calculateBadmintonFee_NormalMember_NotTuesday_NotFriday_Age14_Return1800() {
        viewModel.ageChanged(14)
        viewModel.genderChangedMale(true)
        viewModel.dayOfWeek = DayOfWeek.WEDNESDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BASE_BADMINTON_FEE, viewModel.fee.value)
    }

    /**
     * Age: = 65
     * Gender: Any
     * DayOfWeek: Not Tuesday, not Friday
     */
    @Test
    fun calculateBadmintonFee_NormalMember_NotTuesday_NotFriday_Age65_Return1800() {
        viewModel.ageChanged(65)
        viewModel.genderChangedMale(true)
        viewModel.dayOfWeek = DayOfWeek.WEDNESDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BASE_BADMINTON_FEE, viewModel.fee.value)
    }

    /**
     * Age: >= 13, <= 65
     * Gender: Any
     * DayOfWeek: Tuesday
     */
    @Test
    fun calculateBadmintonFee_NormalMember_Tuesday_Return1200() {
        viewModel.ageChanged(25)
        viewModel.genderChangedMale(true)
        viewModel.dayOfWeek = DayOfWeek.TUESDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BADMINTON_FEE_1200, viewModel.fee.value)
    }

    /**
     * Age: >= 13, <= 65
     * Gender: Female
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_Female_NormalMember_Friday_Return1400() {
        viewModel.ageChanged(25)
        viewModel.genderChangedFemale(true)
        viewModel.dayOfWeek = DayOfWeek.FRIDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BADMINTON_FEE_1400, viewModel.fee.value)
    }

    /**
     * Age: = 14
     * Gender: Female
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_Female_NormalMember_Friday_Age14_Return1400() {
        viewModel.ageChanged(14)
        viewModel.genderChangedFemale(true)
        viewModel.dayOfWeek = DayOfWeek.FRIDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BADMINTON_FEE_1400, viewModel.fee.value)
    }

    /**
     * Age: = 65
     * Gender: Female
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_Female_NormalMember_Friday_Age65_Return1400() {
        viewModel.ageChanged(65)
        viewModel.genderChangedFemale(true)
        viewModel.dayOfWeek = DayOfWeek.FRIDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BADMINTON_FEE_1400, viewModel.fee.value)
    }

    /**
     * Age: >= 13, <= 65
     * Gender: Male
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_Male_NormalMember_Friday_Return1800() {
        viewModel.ageChanged(25)
        viewModel.genderChangedMale(true)
        viewModel.dayOfWeek = DayOfWeek.FRIDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BASE_BADMINTON_FEE, viewModel.fee.value)
    }

    /**
     * Age: < 13
     * Gender: Any
     * DayOfWeek: Not Tuesday, not Friday
     */
    @Test
    fun calculateBadmintonFee_ChildrenMember_NotTuesday_NotFriday_Return900() {
        viewModel.ageChanged(8)
        viewModel.genderChangedMale(true)
        viewModel.dayOfWeek = DayOfWeek.WEDNESDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BASE_BADMINTON_FEE / 2, viewModel.fee.value)
    }

    /**
     * Age: = 13
     * Gender: Any
     * DayOfWeek: Not Tuesday, not Friday
     */
    @Test
    fun calculateBadmintonFee_ChildrenMember_NotTuesday_NotFriday_Age13_Return900() {
        viewModel.ageChanged(13)
        viewModel.genderChangedMale(true)
        viewModel.dayOfWeek = DayOfWeek.WEDNESDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BASE_BADMINTON_FEE / 2, viewModel.fee.value)
    }


    /**
     * Age: < 13
     * Gender: Any
     * DayOfWeek: Tuesday
     */
    @Test
    fun calculateBadmintonFee_ChildrenMember_Tuesday_Return1200() {
        viewModel.ageChanged(8)
        viewModel.genderChangedMale(true)
        viewModel.dayOfWeek = DayOfWeek.TUESDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BADMINTON_FEE_1200, viewModel.fee.value)
    }

    /**
     * Age: = 13
     * Gender: Any
     * DayOfWeek: Tuesday
     */
    @Test
    fun calculateBadmintonFee_ChildrenMember_Tuesday_Age13_Return1200() {
        viewModel.ageChanged(13)
        viewModel.genderChangedMale(true)
        viewModel.dayOfWeek = DayOfWeek.TUESDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BADMINTON_FEE_1200, viewModel.fee.value)
    }

    /**
     * Age: < 13
     * Gender: Any
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_ChildrenMember_Friday_Return900() {
        viewModel.ageChanged(8)
        viewModel.genderChangedFemale(true)
        viewModel.dayOfWeek = DayOfWeek.FRIDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BASE_BADMINTON_FEE / 2, viewModel.fee.value)
    }

    /**
     * Age: = 13
     * Gender: Any
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_ChildrenMember_Friday_Age13_Return900() {
        viewModel.ageChanged(13)
        viewModel.genderChangedFemale(true)
        viewModel.dayOfWeek = DayOfWeek.FRIDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BASE_BADMINTON_FEE / 2, viewModel.fee.value)
    }

    /**
     * Age: > 65
     * Gender: Any
     * DayOfWeek: Not Tuesday, not Friday
     */
    @Test
    fun calculateBadmintonFee_OldMember_NotTuesday_NotFriday_Return1600() {
        viewModel.ageChanged(72)
        viewModel.genderChangedMale(true)
        viewModel.dayOfWeek = DayOfWeek.WEDNESDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BADMINTON_FEE_1600, viewModel.fee.value)
    }

    /**
     * Age: = 66
     * Gender: Any
     * DayOfWeek: Not Tuesday, not Friday
     */
    @Test
    fun calculateBadmintonFee_OldMember_NotTuesday_NotFriday_Age66_Return1600() {
        viewModel.ageChanged(66)
        viewModel.genderChangedMale(true)
        viewModel.dayOfWeek = DayOfWeek.WEDNESDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BADMINTON_FEE_1600, viewModel.fee.value)
    }

    /**
     * Age: > 65
     * Gender: Any
     * DayOfWeek: Tuesday
     */
    @Test
    fun calculateBadmintonFee_OldMember_Tuesday_Return1200() {
        viewModel.ageChanged(72)
        viewModel.genderChangedMale(true)
        viewModel.dayOfWeek = DayOfWeek.TUESDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BADMINTON_FEE_1200, viewModel.fee.value)
    }

    /**
     * Age: = 66
     * Gender: Any
     * DayOfWeek: Tuesday
     */
    @Test
    fun calculateBadmintonFee_OldMember_Tuesday_Age66_Return1200() {
        viewModel.ageChanged(66)
        viewModel.genderChangedFemale(true)
        viewModel.dayOfWeek = DayOfWeek.TUESDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BADMINTON_FEE_1200, viewModel.fee.value)
    }

    /**
     * Age: > 65
     * Gender: Female
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_Female_OldMember_Friday_Return1400() {
        viewModel.ageChanged(72)
        viewModel.genderChangedFemale(true)
        viewModel.dayOfWeek = DayOfWeek.FRIDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BADMINTON_FEE_1400, viewModel.fee.value)
    }

    /**
     * Age: = 66
     * Gender: Female
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_Female_OldMember_Friday_Age66_Return1400() {
        viewModel.ageChanged(66)
        viewModel.genderChangedFemale(true)
        viewModel.dayOfWeek = DayOfWeek.FRIDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BADMINTON_FEE_1400, viewModel.fee.value)
    }

    /**
     * Age: > 65
     * Gender: Male
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_Male_OldMember_Friday_Return1600() {
        viewModel.ageChanged(72)
        viewModel.genderChangedMale(true)
        viewModel.dayOfWeek = DayOfWeek.FRIDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BADMINTON_FEE_1600, viewModel.fee.value)
    }

    /**
     * Age: = 66
     * Gender: Male
     * DayOfWeek: Friday
     */
    @Test
    fun calculateBadmintonFee_Male_OldMember_Friday_Age66_Return1600() {
        viewModel.ageChanged(66)
        viewModel.genderChangedMale(true)
        viewModel.dayOfWeek = DayOfWeek.FRIDAY
        viewModel.calculateBadmintonFee()
        assertEquals(BADMINTON_FEE_1600, viewModel.fee.value)
    }
}