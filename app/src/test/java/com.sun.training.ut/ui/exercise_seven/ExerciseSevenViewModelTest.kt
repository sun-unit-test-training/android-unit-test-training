package com.sun.training.ut.ui.exercise_seven

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.BaseTestViewModel
import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.reflect.Whitebox

/**

Case for unit test
No.	                        1	2	3	4	5	6	7	8
Thành viên premium	        Y	Y	Y	Y	N	N	N	N
Mua hàng trên 5,000円/yên   Y	Y	N	N	Y	Y	N	N
Giao hàng siêu tốc  	    Y	N	Y	N	Y	N	Y	N

Phí vận chuyển 0 yên	    -	X	-	X	-	X	-	-
Phí vận chuyển 500 yên	    X	-	X	-	X	-	-	X
Phí vận chuyển 1000 yên	    -	-	-	-	-	-	X	-
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(value = [ExerciseSevenViewModel::class])
class ExerciseSevenViewModelTest : BaseTestViewModel<ExerciseSevenViewModel>() {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    override fun setUp() {
        super.setUp()
        viewModel = spyk(ExerciseSevenViewModel(), recordPrivateCalls = true)
    }


    @Test
    fun calculateFeeTest_MoneySmaller_0() {
        viewModel.money = -1
        every { viewModel["calculateError"]() } returns Unit

        viewModel.calculateFee()

        verify(exactly = 1) { viewModel["calculateError"]() }
        verify(exactly = 0) {
            viewModel["calculateMoney"]()
            viewModel["calculateMoney5000"]()
        }
    }

    @Test
    fun calculateFeeTest_Money_0() {
        viewModel.money = 0
        every { viewModel["calculateError"]() } returns Unit

        viewModel.calculateFee()

        verify(exactly = 1) { viewModel["calculateError"]() }
        verify(exactly = 0) {
            viewModel["calculateMoney"]()
            viewModel["calculateMoney5000"]()
        }
    }

    @Test
    fun calculateFeeTest_MoneySmaller_5000() {
        viewModel.money = 100
        every { viewModel["calculateMoney"]() } returns Unit

        viewModel.calculateFee()

        verify(exactly = 1) { viewModel["calculateMoney"]() }
        verify(exactly = 0) {
            viewModel["calculateError"]()
            viewModel["calculateMoney5000"]()
        }
    }

    @Test
    fun calculateFeeTest_Money_5000() {
        viewModel.money = 5000
        every { viewModel["calculateMoney5000"]() } returns Unit

        viewModel.calculateFee()

        verify(exactly = 1) { viewModel["calculateMoney5000"]() }
        verify(exactly = 0) {
            viewModel["calculateError"]()
            viewModel["calculateMoney"]()
        }
    }

    @Test
    fun calculateFeeTest_MoneyGreater_5000() {
        viewModel.money = 10000
        every { viewModel["calculateMoney5000"]() } returns Unit

        viewModel.calculateFee()

        verify(exactly = 1) { viewModel["calculateMoney5000"]() }
        verify(exactly = 0) {
            viewModel["calculateError"]()
            viewModel["calculateMoney"]()
        }
    }

    @Test
    fun calculateErrorTest() {
        Whitebox.invokeMethod<Any>(viewModel, "calculateError")

        Assert.assertNotNull(viewModel.isErrorLiveData.value)
        viewModel.isErrorLiveData.value?.apply {
            Assert.assertTrue(this)
        }
        Assert.assertEquals(viewModel.feeLiveData.value, 0)
    }

    // Case 7 No Premium and FastShipping, Money < 5000
    @Test
    fun calculateMoney_Case7() {
        // set No Premium
        Whitebox.invokeMethod<Any>(viewModel, "onPremiumChecked", false)
        // set FastShipping
        Whitebox.invokeMethod<Any>(viewModel, "onFastShippingChecked", true)

        Whitebox.invokeMethod<Any>(viewModel, "calculateMoney")

        Assert.assertFalse(viewModel.isErrorLiveData.value!!)
        Assert.assertEquals(viewModel.feeLiveData.value, 1000)
    }

    // Case 8 No Premium and No FastShipping, Money < 5000
    @Test
    fun calculateMoney_Case8() {
        // set No Premium
        Whitebox.invokeMethod<Any>(viewModel, "onPremiumChecked", false)
        // set No FastShipping
        Whitebox.invokeMethod<Any>(viewModel, "onFastShippingChecked", false)

        Whitebox.invokeMethod<Any>(viewModel, "calculateMoney")

        Assert.assertFalse(viewModel.isErrorLiveData.value!!)
        Assert.assertEquals(viewModel.feeLiveData.value, 500)
    }

    // Case 4  Premium and No FastShipping, Money < 5000
    @Test
    fun calculateMoney_Case4() {
        // set Premium
        Whitebox.invokeMethod<Any>(viewModel, "onPremiumChecked", true)
        // set No FastShipping
        Whitebox.invokeMethod<Any>(viewModel, "onFastShippingChecked", false)

        Whitebox.invokeMethod<Any>(viewModel, "calculateMoney")

        Assert.assertFalse(viewModel.isErrorLiveData.value!!)
        Assert.assertEquals(viewModel.feeLiveData.value, 0)
    }

    // Case 3  Premium and FastShipping, Money < 5000
    @Test
    fun calculateMoney_Case3() {
        // set Premium
        Whitebox.invokeMethod<Any>(viewModel, "onPremiumChecked", true)
        // set FastShipping
        Whitebox.invokeMethod<Any>(viewModel, "onFastShippingChecked", true)

        Whitebox.invokeMethod<Any>(viewModel, "calculateMoney")

        Assert.assertFalse(viewModel.isErrorLiveData.value!!)
        Assert.assertEquals(viewModel.feeLiveData.value, 500)
    }

    // Case 1,5 and FastShipping, Money >= 5000
    @Test
    fun calculateMoney5000_Case1_5() {
        // set FastShipping
        Whitebox.invokeMethod<Any>(viewModel, "onFastShippingChecked", true)

        Whitebox.invokeMethod<Any>(viewModel, "calculateMoney5000")

        Assert.assertFalse(viewModel.isErrorLiveData.value!!)
        Assert.assertEquals(viewModel.feeLiveData.value, 500)
    }

    // Case 2,6 and No FastShipping, Money >= 5000
    @Test
    fun calculateMoney5000_Case2_6() {
        // set No FastShipping
        Whitebox.invokeMethod<Any>(viewModel, "onFastShippingChecked", false)

        Whitebox.invokeMethod<Any>(viewModel, "calculateMoney5000")

        Assert.assertFalse(viewModel.isErrorLiveData.value!!)
        Assert.assertEquals(viewModel.feeLiveData.value, 0)
    }

    @Test
    fun onFastShippingCheckedTest() {
        val isFastShippingBefore: Boolean = Whitebox.getInternalState<Boolean>(viewModel, "isFastShipping")
        Assert.assertFalse(isFastShippingBefore)

        viewModel.onFastShippingChecked(true)
        val isFastShippingTrue: Boolean =
            Whitebox.getInternalState<Boolean>(viewModel, "isFastShipping")
        Assert.assertTrue(isFastShippingTrue)

        viewModel.onFastShippingChecked(false)
        val isFastShippingFalse: Boolean =
            Whitebox.getInternalState<Boolean>(viewModel, "isFastShipping")
        Assert.assertFalse(isFastShippingFalse)
    }

    @Test
    fun onPremiumCheckedTest() {
        val isPremiumBefore: Boolean = Whitebox.getInternalState<Boolean>(viewModel, "isPremium")
        Assert.assertFalse(isPremiumBefore)

        viewModel.onPremiumChecked(true)
        val isPremiumTrue: Boolean = Whitebox.getInternalState<Boolean>(viewModel, "isPremium")
        Assert.assertTrue(isPremiumTrue)

        viewModel.onPremiumChecked(false)
        val isPremiumFalse: Boolean = Whitebox.getInternalState<Boolean>(viewModel, "isPremium")
        Assert.assertFalse(isPremiumFalse)
    }
}