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
        val input = Customer(hour = 8, minute = 45, isVip = true, dayOfMonth = 1, monthOfYear = 9)
        val result = viewModel.calculateFee(customer = input)
        assertEquals(0, result)
    }

    @Test
    fun validatePrice_vip_dayOfWeek_timeOutLower_return0() {
        val input = Customer(hour = 8, minute = 44, isVip = true, dayOfMonth = 29, monthOfYear = 4)
        val result = viewModel.calculateFee(customer = input)
        assertEquals(0, result)
    }

    @Test
    fun validatePrice_vip_dayOfWeek_timeOutUpper_return0() {
        val input = Customer(hour = 18, minute = 0, isVip = true, dayOfMonth = 29, monthOfYear = 4)
        val result = viewModel.calculateFee(customer = input)
        assertEquals(0, result)
    }

    /**
     * Vip
     * Holiday
     * Time ( In, Out Upper, Out Lower )
     * **/

    @Test
    fun validatePrice_vip_holiday_timeIn_return0() {
        val input = Customer(hour = 8, minute = 45, isVip = true, dayOfMonth = 1, monthOfYear = 5)
        val result = viewModel.calculateFee(customer = input)
        assertEquals(0, result)
    }

    @Test
    fun validatePrice_vip_holiday_timeOutUpper_return0() {
        val input = Customer(hour = 18, minute = 0, isVip = true, dayOfMonth = 30, monthOfYear = 4)
        val result = viewModel.calculateFee(customer = input)
        assertEquals(0, result)
    }

    @Test
    fun validatePrice_vip_holiday_timeOutLower_return0() {
        val input = Customer(hour = 8, minute = 44, isVip = true, dayOfMonth = 30, monthOfYear = 4)
        val result = viewModel.calculateFee(customer = input)
        assertEquals(0, result)
    }

    /**
     * NoVip
     * Day of week
     * Time ( In, Out Upper, Out Lower )
     * **/

    @Test
    fun validatePrice_noVip_dayOfWeek_timeIn_return0() {
        val input =
            Customer(hour = 17, minute = 59, isVip = false, dayOfMonth = 30, monthOfYear = 2)
        val result = viewModel.calculateFee(customer = input)
        assertEquals(0, result)
    }

    @Test
    fun validatePrice_noVip_dayOfWeek_timeOutLower_return110() {
        val input = Customer(hour = 8, minute = 44, isVip = false, dayOfMonth = 30, monthOfYear = 5)
        val result = viewModel.calculateFee(customer = input)
        assertEquals(110, result)
    }

    @Test
    fun validatePrice_noVip_dayOfWeek_timeOutUpper_return110() {
        val input = Customer(hour = 18, minute = 0, isVip = false, dayOfMonth = 30, monthOfYear = 5)
        val result = viewModel.calculateFee(customer = input)
        assertEquals(110, result)
    }


    /**
     * NoVip
     * In Holiday
     * Time ( In, Out Upper, Out Lower )
     * **/

    @Test
    fun validatePrice_noVip_holiday_timeIn_return110() {
        val input = Customer(hour = 8, minute = 45, isVip = false, dayOfMonth = 1, monthOfYear = 1)
        val result = viewModel.calculateFee(customer = input)
        assertEquals(110, result)
    }

    @Test
    fun validatePrice_noVip_holiday_timeOutLower_return110() {
        val input = Customer(hour = 8, minute = 44, isVip = false, dayOfMonth = 30, monthOfYear = 4)
        val result = viewModel.calculateFee(customer = input)
        assertEquals(110, result)
    }

    @Test
    fun validatePrice_noVip_holiday_timeOutUpper_return110() {
        val input = Customer(hour = 18, minute = 0, isVip = false, dayOfMonth = 30, monthOfYear = 4)
        val result = viewModel.calculateFee(customer = input)
        assertEquals(110, result)
    }
}