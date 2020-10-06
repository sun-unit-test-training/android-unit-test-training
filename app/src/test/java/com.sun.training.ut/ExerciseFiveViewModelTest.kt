package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.Constant
import com.sun.training.ut.data.model.Pizza
import com.sun.training.ut.ui.exercise_five.ExerciseFiveViewModel
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
class ExerciseFiveViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseFiveViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseFiveViewModel()
    }

    /**
     * No.	                                1	2	3	4	5	6
     * Trên 1,500円/yên	                    Y	Y	Y	N	N	N
     * Cách thức nhận hàng	                Y	Y	N	Y	Y	N
     * Coupon	                            Y	N	-	Y	N	-
     * Khuyến mại khoai tây	                X	X	X	-	-	-
     * 20%OFF	                            X	-	-	X	-	-
     * Miễn phí pizza thứ 2	                -	-	X	-	-	X
     * Phí thông thường	                    -	X	-	-	X	-
     * **/

    @Test
    fun validateCoupon_Bill_Equal1500_Delivery_coupon_return20OFF() {
        viewModel.totalPrice = 1500
        viewModel.onChangedDelivery(true)
        viewModel.onChangedVoucher(true)
        viewModel.calculateCouponWithPizza()
        assertEquals(Constant.Coupon.OFF_20.coupon, viewModel.discountLiveData.value)
    }

    @Test
    fun validateCoupon_Bill_lesser1500_Delivery_coupon_return20OFF() {
        viewModel.totalPrice = 1499
        viewModel.onChangedDelivery(true)
        viewModel.onChangedVoucher(true)
        viewModel.calculateCouponWithPizza()
        assertEquals(1499, viewModel.totalPrice)
        assertEquals(Constant.Coupon.OFF_20.coupon, viewModel.discountLiveData.value)
    }

    @Test
    fun validateCoupon_Bill_Equal1500_Delivery_no_coupon_returnRegularFee() {
        viewModel.totalPrice = 1500
        viewModel.onChangedDelivery(true)
        viewModel.onChangedVoucher(false)
        viewModel.calculateCouponWithPizza()
        assertEquals(Constant.Coupon.REGULAR_FEE.coupon, viewModel.discountLiveData.value)
    }

    @Test
    fun validateCoupon_Bill_lesser1500_Delivery_no_coupon_returnRegularFee() {
        viewModel.totalPrice = 1499
        viewModel.onChangedDelivery(true)
        viewModel.onChangedVoucher(false)
        viewModel.calculateCouponWithPizza()
        assertEquals(Constant.Coupon.REGULAR_FEE.coupon, viewModel.discountLiveData.value)
    }

    @Test
    fun validateCoupon_Bill_lesser1500_NoDelivery_returnFreePizzaSecond() {
        viewModel.totalPrice = 1499
        viewModel.onChangedDelivery(false)
        viewModel.onChangedVoucher(true)
        viewModel.calculateCouponWithPizza()
        assertEquals(Constant.Coupon.PIZZA_SECOND_FREE.coupon, viewModel.discountLiveData.value)
    }

    @Test
    fun validateCoupon_Bill_Equal1500_NoDelivery_returnFreePizzaSecond() {
        viewModel.totalPrice = 1500
        viewModel.onChangedDelivery(false)
        viewModel.onChangedVoucher(true)
        viewModel.calculateCouponWithPizza()
        assertEquals(Constant.Coupon.PIZZA_SECOND_FREE.coupon, viewModel.discountLiveData.value)
    }

    @Test
    fun validateCoupon_Bill_BiggerThan1500_NoDelivery_returnFreePizzaSecondAndPotatoPromotion() {
        viewModel.totalPrice = 1600
        viewModel.onChangedDelivery(false)
        viewModel.onChangedVoucher(true)
        viewModel.calculateCouponWithPizza()
        val expected =
            Constant.Coupon.POTATO_PROMOTION.coupon + Constant.Coupon.PIZZA_SECOND_FREE.coupon
        assertEquals(expected, viewModel.discountLiveData.value)
    }

    @Test
    fun validateCoupon_Bill_BiggerThan1500_Delivery_no_coupon_returnRegularFeeAndPotatoPromotion() {
        val expected =
            Constant.Coupon.POTATO_PROMOTION.coupon + Constant.Coupon.REGULAR_FEE.coupon
        viewModel.totalPrice = 1600
        viewModel.onChangedDelivery(true)
        viewModel.onChangedVoucher(false)
        viewModel.calculateCouponWithPizza()
        assertEquals(expected, viewModel.discountLiveData.value)
    }

    @Test
    fun validateCoupon_Bill_BiggerThan1500_Delivery_coupon_return20OFFAndPotatoPromotion() {
        val expected = Constant.Coupon.POTATO_PROMOTION.coupon + Constant.Coupon.OFF_20.coupon
        viewModel.totalPrice = 1600
        viewModel.onChangedDelivery(true)
        viewModel.onChangedVoucher(true)
        viewModel.calculateCouponWithPizza()
        assertEquals(expected, viewModel.discountLiveData.value)
    }
}