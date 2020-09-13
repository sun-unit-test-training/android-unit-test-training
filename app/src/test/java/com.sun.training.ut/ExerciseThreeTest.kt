package com.sun.training.ut

import com.sun.training.ut.data.model.Shopping
import com.sun.training.ut.exercise.three.calculateDiscount
import org.junit.Assert.assertEquals
import org.junit.Test

class ExerciseThreeTest {

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
        val input = Shopping(isSevenItems = true, isTie = true, isWhiteShirt = true)
        val result = calculateDiscount(shopping = input)
        assertEquals(result, 12)
    }

    @Test
    fun validateDiscount_7Items_NoWhiteShirt_tie_return7() {
        val input = Shopping(isSevenItems = true, isTie = true, isWhiteShirt = false)
        val result = calculateDiscount(shopping = input)
        assertEquals(result, 7)
    }

    @Test
    fun validateDiscount_7Items_whiteShirt_noTie_return7() {
        val input = Shopping(isSevenItems = true, isTie = false, isWhiteShirt = true)
        val result = calculateDiscount(shopping = input)
        assertEquals(result, 7)
    }

    @Test
    fun validateDiscount_7Items_NoWhiteShirt_noTie_return7() {
        val input = Shopping(isSevenItems = true, isTie = false, isWhiteShirt = false)
        val result = calculateDiscount(shopping = input)
        assertEquals(result, 7)
    }

    @Test
    fun validateDiscount_LesserThan7Items_whiteShirt_tie_return5() {
        val input = Shopping(isSevenItems = false, isTie = true, isWhiteShirt = true)
        val result = calculateDiscount(shopping = input)
        assertEquals(result, 5)
    }

    @Test
    fun validateDiscount_LesserThan7Items_NoWhiteShirt_tie_return0() {
        val input = Shopping(isSevenItems = false, isTie = true, isWhiteShirt = false)
        val result = calculateDiscount(shopping = input)
        assertEquals(result, 0)
    }

    @Test
    fun validateDiscount_LesserThan7Items_WhiteShirt_NoTie_return0() {
        val input = Shopping(isSevenItems = false, isTie = false, isWhiteShirt = true)
        val result = calculateDiscount(shopping = input)
        assertEquals(result, 0)
    }

    @Test
    fun validateDiscount_LesserThan7Items_NoWhiteShirt_NoTie_return0() {
        val input = Shopping(isSevenItems = false, isTie = false, isWhiteShirt = false)
        val result = calculateDiscount(shopping = input)
        assertEquals(result, 0)
    }

}