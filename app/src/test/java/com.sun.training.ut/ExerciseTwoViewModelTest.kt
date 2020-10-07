package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.model.Customer
import com.sun.training.ut.ui.exercise_two.ExerciseTwoViewModel
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
class ExerciseTwoViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseTwoViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseTwoViewModel()
    }

    /**
    No.	            1	2	3	4	5
    Ngày thường	    -	Y	Y	N	N
    8:45～17:59	    -	Y	N	Y	N
    Khách hàng vip	Y	N	N	N	N
    Phí 0円/yên	    X	X	-	-	-
    Phí 110円/yên	-	-	X	X	X
     **/

    /**
     * Vip
     * Day of week
     * Time ( In, Out Upper, Out Lower )
     * **/

    @Test
    fun validatePrice_vip_dayOfWeek_timeIn_return0() {
        viewModel.onTimeChanged(8, 45)
        viewModel.onDateChanged(9, 1)
        viewModel.onVipChecked(true)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validatePrice_vip_dayOfWeek_timeOutLower_return0() {
        viewModel.onTimeChanged(8, 44)
        viewModel.onDateChanged(29, 4)
        viewModel.onVipChecked(true)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validatePrice_vip_dayOfWeek_timeOutUpper_return0() {
        viewModel.onTimeChanged(18, 0)
        viewModel.onDateChanged(29, 4)
        viewModel.onVipChecked(true)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    /**
     * Vip
     * Holiday
     * Time ( In, Out Upper, Out Lower )
     * **/

    @Test
    fun validatePrice_vip_holiday_timeIn_return0() {
        viewModel.onTimeChanged(8, 45)
        viewModel.onDateChanged(1, 5)
        viewModel.onVipChecked(true)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validatePrice_vip_holiday_timeOutUpper_return0() {
        viewModel.onTimeChanged(18, 0)
        viewModel.onDateChanged(30, 4)
        viewModel.onVipChecked(true)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validatePrice_vip_holiday_timeOutLower_return0() {
        viewModel.onTimeChanged(8, 44)
        viewModel.onDateChanged(30, 4)
        viewModel.onVipChecked(true)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    /**
     * NoVip
     * Day of week
     * Time ( In, Out Upper, Out Lower )
     * **/

    @Test
    fun validatePrice_noVip_dayOfWeek_timeIn_return0() {
        viewModel.onTimeChanged(17, 59)
        viewModel.onDateChanged(30, 2)
        viewModel.onVipChecked(false)
        viewModel.calculateFee()
        assertEquals(0, viewModel.feeLiveData.value)
    }

    @Test
    fun validatePrice_noVip_dayOfWeek_timeOutLower_return110() {
        viewModel.onTimeChanged(8, 44)
        viewModel.onDateChanged(30, 5)
        viewModel.onVipChecked(false)
        viewModel.calculateFee()
        assertEquals(110, viewModel.feeLiveData.value)
    }

    @Test
    fun validatePrice_noVip_dayOfWeek_timeOutUpper_return110() {
        viewModel.onTimeChanged(18, 0)
        viewModel.onDateChanged(30, 5)
        viewModel.onVipChecked(false)
        viewModel.calculateFee()
        assertEquals(110, viewModel.feeLiveData.value)
    }


    /**
     * NoVip
     * In Holiday
     * Time ( In, Out Upper, Out Lower )
     * **/

    @Test
    fun validatePrice_noVip_holiday_timeIn_return110() {
        viewModel.onTimeChanged(8, 45)
        viewModel.onDateChanged(1, 1)
        viewModel.onVipChecked(false)
        viewModel.calculateFee()
        assertEquals(110, viewModel.feeLiveData.value)
    }

    @Test
    fun validatePrice_noVip_holiday_timeOutLower_return110() {
        viewModel.onTimeChanged(8, 44)
        viewModel.onDateChanged(30, 3)
        viewModel.onVipChecked(false)
        viewModel.calculateFee()
        assertEquals(110, viewModel.feeLiveData.value)
    }

    @Test
    fun validatePrice_noVip_holiday_timeOutUpper_return110() {
        viewModel.onTimeChanged(18, 0)
        viewModel.onDateChanged(30, 3)
        viewModel.onVipChecked(false)
        viewModel.calculateFee()
        assertEquals(110, viewModel.feeLiveData.value)
    }
}