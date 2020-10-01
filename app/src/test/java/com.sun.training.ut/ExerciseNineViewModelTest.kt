package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.data.model.No9Input
import com.sun.training.ut.data.model.No9Result
import com.sun.training.ut.ui.exercise_nine.ExerciseNineViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExerciseNineViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseNineViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseNineViewModel()
    }

    /**
    ◎ Exercise
    Trong Roll Playng Game "Hanoi quest", để dũng sĩ Bánh Mì có thể đánh 「Big Boss」hùng mạnh, cần phải hoàn thành rất nhiều điều kiện.
    ---
    ● Điều kiện tiên quyết：
    ①：Để tìm được căn phòng nơi có Big Boss, cần mang theo công cụ là "Đũa phép" hoặc là cần có "Quân sư" làm bạn đồng hành
    ②：Khi vào trong phòng có Big Boss , cần mang theo công cụ là "Chìa khoá bóng đêm".
    ③：Để đánh bại Big Boss, dũng sĩ Bánh Mì cần phải mang theo vũ khí là "Kiếm ánh sáng"

    Hãy tham khảo các điều kiện bên trên và viết ra các unit test case tối thiểu nhất bạn có thể nghĩ được trong trường hợp bạn đang implement chương trình đó.

    Case for unit test
    No.	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16
    Có đũa phép	            Y	Y	Y	Y	Y	Y	N	N	N	N	Y	Y	Y	Y	N	N
    Có quân sư đồng hành	Y	Y	Y	N	N	N	Y	Y	Y	N	Y	Y	N	N	Y	Y
    Có chìa khoá bóng đêm	Y	Y	N	Y	Y	N	Y	Y	N	-	N	N	N	N	N	N
    Có kiếm ánh sáng	    Y	N	-	Y	N	-	Y	N	-	-	Y	N	Y	N	Y	N
    Không tìm thấy phòng	-	-	-	-	-	-	-	-	-	X	-	-	-	-	-	-
    Tìm thấy phòng	        X	X	X	X	X	X	X	X	X	-	X	X	X	X	X	X
    Vào phòng	            X	X	-	X	X	-	X	X	-	-	-	-	-	-	-	-
    Đánh bại Big Boss	    X	-	-	X	-	-	X	-	-	-	-	-	-	-	-	-
     */

    @Test
    fun checkExercise9Case1_FullTool_BeatBoss() {
        viewModel.onMagicWandChecked(true)
        viewModel.onMasterChecked(true)
        viewModel.onKeyChecked(true)
        viewModel.onLightSwordChecked(true)
        val outputExpect = No9Result(findRoom = true, inputRoom = true, beatBoss = true)
        viewModel.checkResultExerciseNine()

        assertEquals(outputExpect, viewModel.resultBeatBoss.value)
    }

    @Test
    fun checkExercise9Case2_NoLightSword_NoBeatBoss() {
        viewModel.onMagicWandChecked(true)
        viewModel.onMasterChecked(true)
        viewModel.onKeyChecked(true)
        viewModel.onLightSwordChecked(false)
        val outputExpect = No9Result(findRoom = true, inputRoom = true, beatBoss = null)
        viewModel.checkResultExerciseNine()

        assertEquals(outputExpect, viewModel.resultBeatBoss.value)
    }

    @Test
    fun checkExercise9Case3_NoKey_NoInputRoom() {
        viewModel.onMagicWandChecked(true)
        viewModel.onMasterChecked(true)
        viewModel.onKeyChecked(false)
        val outputExpect = No9Result(findRoom = true, inputRoom = null, beatBoss = null)
        viewModel.checkResultExerciseNine()

        assertEquals(outputExpect, viewModel.resultBeatBoss.value)
    }

    @Test
    fun checkExercise9Case4_AllToolExceptMaster_BeatBoss() {
        viewModel.onMagicWandChecked(true)
        viewModel.onMasterChecked(false)
        viewModel.onKeyChecked(true)
        viewModel.onLightSwordChecked(true)
        val outputExpect = No9Result(findRoom = true, inputRoom = true, beatBoss = true)
        viewModel.checkResultExerciseNine()

        assertEquals(outputExpect, viewModel.resultBeatBoss.value)
    }

    @Test
    fun checkExercise9Case5_NoMasterAndLightSword_NoBeatBoss() {
        viewModel.onMagicWandChecked(true)
        viewModel.onMasterChecked(false)
        viewModel.onKeyChecked(true)
        viewModel.onLightSwordChecked(false)
        val outputExpect = No9Result(findRoom = true, inputRoom = true, beatBoss = null)
        viewModel.checkResultExerciseNine()

        assertEquals(outputExpect, viewModel.resultBeatBoss.value)
    }

    @Test
    fun checkExercise9Case6_NoMasterAndKey_NoInputRoom() {
        viewModel.onMagicWandChecked(true)
        viewModel.onMasterChecked(false)
        viewModel.onKeyChecked(false)
        val outputExpect = No9Result(findRoom = true, inputRoom = null, beatBoss = null)
        viewModel.checkResultExerciseNine()

        assertEquals(outputExpect, viewModel.resultBeatBoss.value)
    }

    @Test
    fun checkExercise9Case7_NoMagicWand_BeatBoss() {
        viewModel.onMagicWandChecked(false)
        viewModel.onMasterChecked(true)
        viewModel.onKeyChecked(true)
        viewModel.onLightSwordChecked(true)
        val outputExpect = No9Result(findRoom = true, inputRoom = true, beatBoss = true)
        viewModel.checkResultExerciseNine()

        assertEquals(outputExpect, viewModel.resultBeatBoss.value)
    }

    @Test
    fun checkExercise9Case8_NoMagicWandAndLightSword_NoBeatBoss() {
        viewModel.onMagicWandChecked(false)
        viewModel.onMasterChecked(true)
        viewModel.onKeyChecked(true)
        viewModel.onLightSwordChecked(false)
        val outputExpect = No9Result(findRoom = true, inputRoom = true, beatBoss = null)
        viewModel.checkResultExerciseNine()

        assertEquals(outputExpect, viewModel.resultBeatBoss.value)
    }

    @Test
    fun checkExercise9Case9_NoMagicWandAndKey_NoInputRoom() {
        viewModel.onMagicWandChecked(false)
        viewModel.onMasterChecked(true)
        viewModel.onKeyChecked(false)
        val outputExpect = No9Result(findRoom = true, inputRoom = null, beatBoss = null)
        viewModel.checkResultExerciseNine()

        assertEquals(outputExpect, viewModel.resultBeatBoss.value)
    }

    @Test
    fun checkExercise9Case10_MissAll_NoFindRoom() {
        viewModel.onMagicWandChecked(false)
        viewModel.onMasterChecked(false)
        val outputExpect = No9Result(findRoom = false, inputRoom = null, beatBoss = null)
        viewModel.checkResultExerciseNine()

        assertEquals(outputExpect, viewModel.resultBeatBoss.value)
    }

    @Test
    fun checkExercise9Case11_NoKey_NoInputRoom() {
        viewModel.onMagicWandChecked(true)
        viewModel.onMasterChecked(true)
        viewModel.onKeyChecked(false)
        viewModel.onLightSwordChecked(true)
        val outputExpect = No9Result(findRoom = true, inputRoom = null, beatBoss = null)
        viewModel.checkResultExerciseNine()

        assertEquals(outputExpect, viewModel.resultBeatBoss.value)
    }

    @Test
    fun checkExercise9Case12_NoKeyAndLightSword_NoInputRoom() {
        viewModel.onMagicWandChecked(true)
        viewModel.onMasterChecked(true)
        viewModel.onKeyChecked(false)
        viewModel.onLightSwordChecked(false)
        val outputExpect = No9Result(findRoom = true, inputRoom = null, beatBoss = null)
        viewModel.checkResultExerciseNine()

        assertEquals(outputExpect, viewModel.resultBeatBoss.value)
    }

    @Test
    fun checkExercise9Case13_NoMasterAndKey_NoInputRoom() {
        viewModel.onMagicWandChecked(true)
        viewModel.onMasterChecked(false)
        viewModel.onKeyChecked(false)
        viewModel.onLightSwordChecked(true)
        val outputExpect = No9Result(findRoom = true, inputRoom = null, beatBoss = null)
        viewModel.checkResultExerciseNine()

        assertEquals(outputExpect, viewModel.resultBeatBoss.value)
    }

    @Test
    fun checkExercise9Case14_NoMasterAndKey_NoInputRoom() {
        viewModel.onMagicWandChecked(true)
        viewModel.onMasterChecked(false)
        viewModel.onKeyChecked(false)
        viewModel.onLightSwordChecked(false)
        val outputExpect = No9Result(findRoom = true, inputRoom = null, beatBoss = null)
        viewModel.checkResultExerciseNine()

        assertEquals(outputExpect, viewModel.resultBeatBoss.value)
    }

    @Test
    fun checkExercise9Case15_NoMagicWandAndKey_NoInputRoom() {
        viewModel.onMagicWandChecked(false)
        viewModel.onMasterChecked(true)
        viewModel.onKeyChecked(false)
        viewModel.onLightSwordChecked(true)
        val outputExpect = No9Result(findRoom = true, inputRoom = null, beatBoss = null)
        viewModel.checkResultExerciseNine()

        assertEquals(outputExpect, viewModel.resultBeatBoss.value)
    }

    @Test
    fun checkExercise9Case16_OnlyMaster_NoInputRoom() {
        viewModel.onMagicWandChecked(false)
        viewModel.onMasterChecked(true)
        viewModel.onKeyChecked(false)
        viewModel.onLightSwordChecked(false)
        val outputExpect = No9Result(findRoom = true, inputRoom = null, beatBoss = null)
        viewModel.checkResultExerciseNine()

        assertEquals(outputExpect, viewModel.resultBeatBoss.value)
    }
}
