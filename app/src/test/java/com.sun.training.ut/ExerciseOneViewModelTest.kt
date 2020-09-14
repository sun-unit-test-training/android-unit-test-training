package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.Constant
import com.sun.training.ut.data.model.Beer
import com.sun.training.ut.ui.exercise_one.ExerciseOneViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ExerciseOneViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseOneViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseOneViewModel()
    }

    /**
    No.	                        1	2	3
    voucher	                    Y	N	N
    Đang trong thời gian ưu đãi	-	Y	N
    100円/yên	                X	-	-
    290円/yên	                -	X	-
    490円/yên	                -	-	X
     **/

    @Test
    fun validatePrice_withVoucher_noTimeDiscount_numberBeerIs5_returnPriceIs500() {
        val input = Beer(isVoucher = true, isTimeCoupon = false, numberBeer = 5)
        val result = viewModel.calculatePrice(input)
        assertEquals(input.numberBeer * Constant.VOUCHER_PRICE, result)
    }

    @Test
    fun validatePrice_withVoucher_withTimeDiscount_numberBeerIs5_returnPriceIs500() {
        val input = Beer(isVoucher = true, isTimeCoupon = true, numberBeer = 5)
        val result = viewModel.calculatePrice(beer = input)
        assertEquals(input.numberBeer * Constant.VOUCHER_PRICE, result)
    }

    @Test
    fun validatePrice_noVoucher_onTimeDiscount_numberBeerIs10_returnPriceIs2900() {
        val input = Beer(isVoucher = false, isTimeCoupon = true, numberBeer = 10)
        val result = viewModel.calculatePrice(beer = input)
        assertEquals(input.numberBeer * Constant.TIME_PRICE, result)
    }

    @Test
    fun validatePrice_noVoucher_NoTimeDiscount_numberBeerIs7_returnPriceIs3430() {
        val input = Beer(isVoucher = false, isTimeCoupon = false, numberBeer = 7)
        val result = viewModel.calculatePrice(beer = input)
        assertEquals(input.numberBeer * Constant.REGULAR_PRICE, result)
    }
}