package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.Constant
import com.sun.training.ut.data.model.Calendar
import com.sun.training.ut.ui.exercise_four.ExerciseFourViewModel
import io.mockk.spyk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.reflect.Whitebox

@RunWith(PowerMockRunner::class)
@PrepareForTest(value = [ExerciseFourViewModel::class])
class ExerciseFourViewModelTest : BaseTestViewModel<ExerciseFourViewModel>() {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    override fun setUp() {
        viewModel = spyk(ExerciseFourViewModel(), recordPrivateCalls = true)
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
        val dataInput =
            Calendar(dayOfMonth = 1, monthOfYear = 1)
        Whitebox.invokeMethod<Any>(viewModel, "calculateColor", dataInput)
        assertEquals(Constant.Color.RED.name, viewModel.colorLiveData.value)
    }

    /**
     * isHoliday: true
     * DayOfWeek: Sunday
     */
    @Test
    fun validateColor_SundayWithHoliday_returnRed() {
        val dataInput =
            Calendar(dayOfMonth = 30, monthOfYear = 4)
        Whitebox.invokeMethod<Any>(viewModel, "calculateColor", dataInput)
        assertEquals(Constant.Color.RED.name, viewModel.colorLiveData.value)
    }

    /**
     * isHoliday: true
     * DayOfWeek: Monday..Friday
     */
    @Test
    fun validateColor_DayOfWeekWithHoliday_returnRed() {
        val dataInput =
            Calendar(dayOfMonth = 1, monthOfYear = 5)
        Whitebox.invokeMethod<Any>(viewModel, "calculateColor", dataInput)
        assertEquals(Constant.Color.RED.name, viewModel.colorLiveData.value)
    }

    /**
     * isHoliday: false
     * DayOfWeek: Saturday
     */
    @Test
    fun validateColor_SaturdayWithNoHoliday_returnBlue() {
        val dataInput =
            Calendar(dayOfMonth = 19, monthOfYear = 9)
        Whitebox.invokeMethod<Any>(viewModel, "calculateColor", dataInput)
        assertEquals(Constant.Color.BLUE.name, viewModel.colorLiveData.value)
    }

    /**
     * isHoliday: false
     * DayOfWeek: Sunday
     */
    @Test
    fun validateColor_SundayWithNoHoliday_returnRed() {
        val dataInput =
            Calendar(dayOfMonth = 20, monthOfYear = 9)
        Whitebox.invokeMethod<Any>(viewModel, "calculateColor", dataInput)
        assertEquals(Constant.Color.RED.name, viewModel.colorLiveData.value)
    }

    /**
     * isHoliday: false
     * DayOfWeek: Monday..Friday
     */
    @Test
    fun validateColor_DayOfWeekWithOutHoliday_returnBlack() {
        val dataInput =
            Calendar(dayOfMonth = 30, monthOfYear = 1)
        Whitebox.invokeMethod<Any>(viewModel, "calculateColor", dataInput)
        assertEquals(Constant.Color.BLACK.name, viewModel.colorLiveData.value)
    }
}