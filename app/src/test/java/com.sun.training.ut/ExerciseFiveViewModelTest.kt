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
        val input =
            Pizza(bill = 1500, typeDelivery = Constant.TypeDelivery.DELIVERY, isCoupon = true)
        val validate = viewModel.calculateCouponWithPizza(pizza = input)
        val expected = Constant.Coupon.OFF_20.coupon
        assertEquals(expected, validate)
    }

    @Test
    fun validateCoupon_Bill_lesser1500_Delivery_coupon_return20OFF() {
        val input =
            Pizza(bill = 1499, typeDelivery = Constant.TypeDelivery.DELIVERY, isCoupon = true)
        val validate = viewModel.calculateCouponWithPizza(pizza = input)
        val expected = Constant.Coupon.OFF_20.coupon
        assertEquals(expected, validate)
    }

    @Test
    fun validateCoupon_Bill_Equal1500_Delivery_no_coupon_returnRegularFee() {
        val input =
            Pizza(bill = 1500, typeDelivery = Constant.TypeDelivery.DELIVERY, isCoupon = false)
        val validate = viewModel.calculateCouponWithPizza(pizza = input)
        val expected = Constant.Coupon.REGULAR_FEE.coupon
        assertEquals(expected, validate)
    }

    @Test
    fun validateCoupon_Bill_lesser1500_Delivery_no_coupon_returnRegularFee() {
        val input =
            Pizza(bill = 1499, typeDelivery = Constant.TypeDelivery.DELIVERY, isCoupon = false)
        val validate = viewModel.calculateCouponWithPizza(pizza = input)
        val expected = Constant.Coupon.REGULAR_FEE.coupon
        assertEquals(expected, validate)
    }

    @Test
    fun validateCoupon_Bill_lesser1500_NoDelivery_returnFreePizzaSecond() {
        val input =
            Pizza(
                bill = 1499,
                typeDelivery = Constant.TypeDelivery.RECEIVE_AT_STORE,
                isCoupon = true
            )
        val validate = viewModel.calculateCouponWithPizza(pizza = input)
        val expected = Constant.Coupon.PIZZA_SECOND_FREE.coupon
        assertEquals(expected, validate)
    }

    @Test
    fun validateCoupon_Bill_Equal1500_NoDelivery_returnFreePizzaSecond() {
        val input =
            Pizza(
                bill = 1500,
                typeDelivery = Constant.TypeDelivery.RECEIVE_AT_STORE,
                isCoupon = true
            )
        val validate = viewModel.calculateCouponWithPizza(pizza = input)
        val expected = Constant.Coupon.PIZZA_SECOND_FREE.coupon
        assertEquals(expected, validate)
    }

    @Test
    fun validateCoupon_Bill_BiggerThan1500_NoDelivery_returnFreePizzaSecondAndPotatoPromotion() {
        val input =
            Pizza(
                bill = 1600,
                typeDelivery = Constant.TypeDelivery.RECEIVE_AT_STORE,
                isCoupon = true
            )
        val validate = viewModel.calculateCouponWithPizza(pizza = input)
        val expected =
            Constant.Coupon.POTATO_PROMOTION.coupon + Constant.Coupon.PIZZA_SECOND_FREE.coupon
        assertEquals(expected, validate)
    }

    @Test
    fun validateCoupon_Bill_BiggerThan1500_Delivery_no_coupon_returnRegularFeeAndPotatoPromotion() {
        val input =
            Pizza(bill = 1600, typeDelivery = Constant.TypeDelivery.DELIVERY, isCoupon = false)
        val validate = viewModel.calculateCouponWithPizza(pizza = input)
        val expected =
            Constant.Coupon.POTATO_PROMOTION.coupon + Constant.Coupon.REGULAR_FEE.coupon
        assertEquals(expected, validate)
    }

    @Test
    fun validateCoupon_Bill_BiggerThan1500_Delivery_coupon_return20OFFAndPotatoPromotion() {
        val input =
            Pizza(bill = 1600, typeDelivery = Constant.TypeDelivery.DELIVERY, isCoupon = true)
        val validate = viewModel.calculateCouponWithPizza(pizza = input)
        val expected = Constant.Coupon.POTATO_PROMOTION.coupon + Constant.Coupon.OFF_20.coupon
        assertEquals(expected, validate)
    }
}