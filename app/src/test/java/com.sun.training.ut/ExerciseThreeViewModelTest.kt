package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.Constant
import com.sun.training.ut.ui.exercise_three.ExerciseThreeViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExerciseThreeViewModelTest {

    /*
    *
    * ◎Exercise①
    * Tại cửa hàng quần áo nam trên Hoàn Kiếm, có thể mua được giảm giá dựa trên các điều kiện sau:
    * ---
    * ●Điều kiện tiên quyết：
    * ①：Trong số các mặt hàng mua, có cả áo sơ mi trắng và cà vạt, thì sẽ được giảm 5% trên tổng hoá đơn.
    * ②：Trường hợp mua từ trên 7 mặt hàng, được giảm 7% trên tổng hoá đơn.
    * ③：Có thể áp dụng đồng thời ưu đãi ① và ②, tức là sẽ được giảm 12% trên tổng hoá đơn.
    * ④：Thứ tự ưu tiên về logic áp dụng giảm giá như bên dưới:
    * ・Có mua từ 7 mặt hàng trở lên hay không?
    * ・Có bao gồm áo sơ mi trắng hay không?
    * ・Có bao gồm cà vạt hay không?
    * */

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseThreeViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseThreeViewModel()
    }

    /**
     * No.	                    1	2	3	4	5	6
     * Có 7 mặt hàng trở lên	Y	Y	Y	N	N	N
     * Có ái sơ mi trắng	    Y	Y	N	Y	Y	N
     * Có cà vạt	            Y	N	-	Y	N	-
     * Giảm giá：12%	            X	-	-	-	-	-
     * Giảm giá：7%	            -	X	X	-	-	-
     * Giảm giá：5%	            -	-	-	X	-	-
     * Giảm giá：0%	            -	-	-	-	X	X
     **/

    /**
     * No.	                    1	2	3	4	5	6	7	8
     * Có 7 mặt hàng trở lên	Y	Y	Y	Y	N	N	N	N
     * Có ái sơ mi trắng	    Y	Y	N	N	Y	Y	N	N
     * Có cà vạt	            Y	N	Y	N	Y	N	Y	N
     * Giảm giá：12%	            X	-	-	-	-	-	-	-
     * Giảm giá：7%	            -	X	X	X	-	-	-	-
     * Giảm giá：5%	            -	-	-	-	X	-	-	-
     * Giảm giá：0%	            -	-	-	-	-	X	X	X
     **/

    @Test
    fun validateDiscount_7Items_whiteShirt_tie_return12() {
        viewModel.numberOfItems = Constant.DEFAULT_ITEM_HAVE_DISCOUNT
        viewModel.onChangedTie(true)
        viewModel.onChangedShirt(true)
        viewModel.calculate()
        assertEquals(Constant.DISCOUNT_12, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_7Items_NoWhiteShirt_tie_return7() {
        viewModel.numberOfItems = Constant.DEFAULT_ITEM_HAVE_DISCOUNT
        viewModel.onChangedTie(true)
        viewModel.onChangedShirt(false)
        viewModel.calculate()
        assertEquals(Constant.DISCOUNT_7, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_7Items_whiteShirt_noTie_return7() {
        viewModel.numberOfItems = Constant.DEFAULT_ITEM_HAVE_DISCOUNT
        viewModel.onChangedTie(false)
        viewModel.onChangedShirt(true)
        viewModel.calculate()
        assertEquals(Constant.DISCOUNT_7, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_7Items_NoWhiteShirt_noTie_return7() {
        viewModel.numberOfItems = Constant.DEFAULT_ITEM_HAVE_DISCOUNT
        viewModel.onChangedTie(false)
        viewModel.onChangedShirt(false)
        viewModel.calculate()
        assertEquals(Constant.DISCOUNT_7, viewModel.discountLiveData.value)
    }

    //>= 7 items

    @Test
    fun validateDiscount_Upper7Items_whiteShirt_tie_return12() {
        viewModel.numberOfItems = 1000
        viewModel.onChangedTie(true)
        viewModel.onChangedShirt(true)
        viewModel.calculate()
        assertEquals(Constant.DISCOUNT_12, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_Upper7Items_NoWhiteShirt_tie_return7() {
        viewModel.numberOfItems = 999
        viewModel.onChangedTie(true)
        viewModel.onChangedShirt(false)
        viewModel.calculate()
        assertEquals(Constant.DISCOUNT_7, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_Upper7Items_whiteShirt_noTie_return7() {
        viewModel.numberOfItems = 99
        viewModel.onChangedTie(false)
        viewModel.onChangedShirt(true)
        viewModel.calculate()
        assertEquals(Constant.DISCOUNT_7, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_Upper7Items_NoWhiteShirt_noTie_return7() {
        viewModel.numberOfItems = 8
        viewModel.onChangedTie(false)
        viewModel.onChangedShirt(false)
        viewModel.calculate()
        assertEquals(Constant.DISCOUNT_7, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_LesserThan7Items_whiteShirt_tie_return5() {
        viewModel.numberOfItems = 6
        viewModel.onChangedTie(true)
        viewModel.onChangedShirt(true)
        viewModel.calculate()
        assertEquals(5, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_LesserThan7Items_NoWhiteShirt_tie_return0() {
        viewModel.numberOfItems = 1
        viewModel.onChangedTie(true)
        viewModel.onChangedShirt(false)
        viewModel.calculate()
        assertEquals(0, viewModel.discountLiveData.value)

    }

    @Test
    fun validateDiscount_LesserThan7Items_WhiteShirt_NoTie_return0() {
        viewModel.numberOfItems = -1
        viewModel.onChangedTie(false)
        viewModel.onChangedShirt(true)
        viewModel.calculate()
        assertEquals(0, viewModel.discountLiveData.value)
    }

    @Test
    fun validateDiscount_LesserThan7Items_NoWhiteShirt_NoTie_return0() {
        viewModel.numberOfItems = 0
        viewModel.onChangedTie(false)
        viewModel.onChangedShirt(false)
        viewModel.calculate()
        assertEquals(0, viewModel.discountLiveData.value)
    }
}