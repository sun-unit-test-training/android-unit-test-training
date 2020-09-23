package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.Constant
import com.sun.training.ut.data.model.Shopping
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
        val input = Shopping(
            numberOfItems = Constant.DEFAULT_ITEM_HAVE_DISCOUNT,
            isTie = true,
            isWhiteShirt = true
        )
        val result = viewModel.calculateDiscount(shopping = input)
        assertEquals(result, Constant.DISCOUNT_12)
    }

    @Test
    fun validateDiscount_7Items_NoWhiteShirt_tie_return7() {
        val input = Shopping(
            numberOfItems = Constant.DEFAULT_ITEM_HAVE_DISCOUNT,
            isTie = true,
            isWhiteShirt = false
        )
        val result = viewModel.calculateDiscount(shopping = input)
        assertEquals(result, Constant.DISCOUNT_7)
    }

    @Test
    fun validateDiscount_7Items_whiteShirt_noTie_return7() {
        val input = Shopping(
            numberOfItems = Constant.DEFAULT_ITEM_HAVE_DISCOUNT,
            isTie = false,
            isWhiteShirt = true
        )
        val result = viewModel.calculateDiscount(shopping = input)
        assertEquals(result, Constant.DISCOUNT_7)
    }

    @Test
    fun validateDiscount_7Items_NoWhiteShirt_noTie_return7() {
        val input = Shopping(
            numberOfItems = Constant.DEFAULT_ITEM_HAVE_DISCOUNT,
            isTie = false,
            isWhiteShirt = false
        )
        val result = viewModel.calculateDiscount(shopping = input)
        assertEquals(result, Constant.DISCOUNT_7)
    }

    //>= 7 items

    @Test
    fun validateDiscount_Upper7Items_whiteShirt_tie_return12() {
        val input = Shopping(
            numberOfItems = 1000,
            isTie = true,
            isWhiteShirt = true
        )
        val result = viewModel.calculateDiscount(shopping = input)
        assertEquals(result, Constant.DISCOUNT_12)
    }

    @Test
    fun validateDiscount_Upper7Items_NoWhiteShirt_tie_return7() {
        val input = Shopping(
            numberOfItems = 999,
            isTie = true,
            isWhiteShirt = false
        )
        val result = viewModel.calculateDiscount(shopping = input)
        assertEquals(result, Constant.DISCOUNT_7)
    }

    @Test
    fun validateDiscount_Upper7Items_whiteShirt_noTie_return7() {
        val input = Shopping(
            numberOfItems = 99,
            isTie = false,
            isWhiteShirt = true
        )
        val result = viewModel.calculateDiscount(shopping = input)
        assertEquals(result, Constant.DISCOUNT_7)
    }

    @Test
    fun validateDiscount_Upper7Items_NoWhiteShirt_noTie_return7() {
        val input = Shopping(
            numberOfItems = 8,
            isTie = false,
            isWhiteShirt = false
        )
        val result = viewModel.calculateDiscount(shopping = input)
        assertEquals(result, Constant.DISCOUNT_7)
    }

    @Test
    fun validateDiscount_LesserThan7Items_whiteShirt_tie_return5() {
        val input = Shopping(numberOfItems = 6, isTie = true, isWhiteShirt = true)
        val result = viewModel.calculateDiscount(shopping = input)
        assertEquals(result, 5)
    }

    @Test
    fun validateDiscount_LesserThan7Items_NoWhiteShirt_tie_return0() {
        val input = Shopping(numberOfItems = 1, isTie = true, isWhiteShirt = false)
        val result = viewModel.calculateDiscount(shopping = input)
        assertEquals(result, 0)
    }

    @Test
    fun validateDiscount_LesserThan7Items_WhiteShirt_NoTie_return0() {
        val input = Shopping(numberOfItems = -1, isTie = false, isWhiteShirt = true)
        val result = viewModel.calculateDiscount(shopping = input)
        assertEquals(result, 0)
    }

    @Test
    fun validateDiscount_LesserThan7Items_NoWhiteShirt_NoTie_return0() {
        val input = Shopping(numberOfItems = 0, isTie = false, isWhiteShirt = false)
        val result = viewModel.calculateDiscount(shopping = input)
        assertEquals(result, 0)
    }
}