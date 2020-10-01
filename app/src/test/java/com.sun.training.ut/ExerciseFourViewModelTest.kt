package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.Constant
import com.sun.training.ut.data.model.Calendar
import com.sun.training.ut.ui.exercise_four.ExerciseFourViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.powermock.reflect.Whitebox

@RunWith(MockitoJUnitRunner::class)
class ExerciseFourViewModelTest() {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseFourViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseFourViewModel()
    }

    /**
     * No.	            1	2	3	4	5	6
     * Ngày lễ	        Y	Y	Y	N	N	N
     * Thứ 7	        Y	N	N	Y	N	N
     * Chủ nhật	        N	Y	N	N	Y	N
     * Ngày thường	    N	N	Y	N	N	Y
     * Màu đỏ	        X	X	X	-	X	-
     * Màu xanh lam	    -	-	-	X	-	-
     * Màu đen	        -	-	-	-	-	X
     * **/

    /**
     * Y：Yes (Điều kiện thích hợp)
     * N：No (Điều kiện không thích hợp)
     * X：Giá trị mong đợi
     * - ：Các điều kiện không gây ảnh hưởng đến kết quả
     * **/

    /**
     * isHoliday: true
     * DayOfWeek: Saturday
     */
    @Test
    fun validateColor_SaturdayWithHoliday_returnRed() {
        viewModel.onDateChanged(0, 1)
        viewModel.calculateColor()
        assertEquals(Constant.Color.RED.name, viewModel.colorLiveData.value)
    }

    /**
     * isHoliday: true
     * DayOfWeek: Sunday
     */
    @Test
    fun validateColor_SundayWithHoliday_returnRed() {
        viewModel.onDateChanged(3, 30)
        viewModel.calculateColor()
        assertEquals(Constant.Color.RED.name, viewModel.colorLiveData.value)
    }

    /**
     * isHoliday: true
     * DayOfWeek: Monday..Friday
     */
    @Test
    fun validateColor_DayOfWeekWithHoliday_returnRed() {
        viewModel.onDateChanged(3, 5)
        viewModel.calculateColor()
        assertEquals(Constant.Color.RED.name, viewModel.colorLiveData.value)
    }

    /**
     * isHoliday: false
     * DayOfWeek: Saturday
     */
    @Test
    fun validateColor_SaturdayWithNoHoliday_returnBlue() {
        viewModel.onDateChanged(8, 19)
        viewModel.calculateColor()
        assertEquals(Constant.Color.BLUE.name, viewModel.colorLiveData.value)
    }

    /**
     * isHoliday: false
     * DayOfWeek: Sunday
     */
    @Test
    fun validateColor_SundayWithNoHoliday_returnRed() {
        viewModel.onDateChanged(8, 20)
        viewModel.calculateColor()
        assertEquals(Constant.Color.RED.name, viewModel.colorLiveData.value)
    }

    /**
     * isHoliday: false
     * DayOfWeek: Monday..Friday
     */
    @Test
    fun validateColor_DayOfWeekWithOutHoliday_returnBlack() {
        viewModel.onDateChanged(0, 30)
        viewModel.calculateColor()
        assertEquals(Constant.Color.BLACK.name, viewModel.colorLiveData.value)
    }
}