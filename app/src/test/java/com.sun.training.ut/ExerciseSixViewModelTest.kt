package com.sun.training.ut

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sun.training.ut.ui.excercise_six.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ExerciseSixViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseSixViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ExerciseSixViewModel()
    }

    @Test
    fun calculateFreeParkingTime_noWatchMovie_1() {
        Assert.assertEquals(0, viewModel.calculateFreeParkingMinute(-1, false))
    }

    @Test
    fun calculateFreeParkingTime_noWatchMovie_2() {
        Assert.assertEquals(0, viewModel.calculateFreeParkingMinute(0, false))
    }

    @Test
    fun calculateFreeParkingTime_noWatchMovie_3() {
        Assert.assertEquals(0, viewModel.calculateFreeParkingMinute(FIRST_MONEY_POINT - 1, false))
    }

    @Test
    fun calculateFreeParkingTime_noWatchMovie_4() {
        Assert.assertEquals(
            FIRST_FREE_TIME,
            viewModel.calculateFreeParkingMinute(FIRST_MONEY_POINT, false)
        )
    }

    @Test
    fun calculateFreeParkingTime_noWatchMovie_5() {
        Assert.assertEquals(
            FIRST_FREE_TIME,
            viewModel.calculateFreeParkingMinute(FIRST_MONEY_POINT + 1, false)
        )
    }

    @Test
    fun calculateFreeParkingTime_noWatchMovie_6() {
        Assert.assertEquals(
            FIRST_FREE_TIME,
            viewModel.calculateFreeParkingMinute(SECOND_MONEY_POINT - 1, false)
        )
    }

    @Test
    fun calculateFreeParkingTime_noWatchMovie_7() {
        Assert.assertEquals(
            SECOND_FREE_TIME,
            viewModel.calculateFreeParkingMinute(SECOND_MONEY_POINT, false)
        )
    }

    @Test
    fun calculateFreeParkingTime_noWatchMovie_8() {
        Assert.assertEquals(
            SECOND_FREE_TIME,
            viewModel.calculateFreeParkingMinute(SECOND_MONEY_POINT + 1, false)
        )
    }

    @Test
    fun calculateFreeParkingTime_watchMovie_1() {
        Assert.assertEquals(WATCH_MOVIE_FREE_TIME, viewModel.calculateFreeParkingMinute(-1, true))
    }

    @Test
    fun calculateFreeParkingTime_watchMovie_2() {
        Assert.assertEquals(WATCH_MOVIE_FREE_TIME, viewModel.calculateFreeParkingMinute(0, true))
    }

    @Test
    fun calculateFreeParkingTime_watchMovie_3() {
        Assert.assertEquals(
            WATCH_MOVIE_FREE_TIME,
            viewModel.calculateFreeParkingMinute(FIRST_MONEY_POINT - 1, true)
        )
    }

    @Test
    fun calculateFreeParkingTime_watchMovie_4() {
        Assert.assertEquals(
            FIRST_FREE_TIME + WATCH_MOVIE_FREE_TIME,
            viewModel.calculateFreeParkingMinute(FIRST_MONEY_POINT, true)
        )
    }

    @Test
    fun calculateFreeParkingTime_watchMovie_5() {
        Assert.assertEquals(
            FIRST_FREE_TIME + WATCH_MOVIE_FREE_TIME,
            viewModel.calculateFreeParkingMinute(FIRST_MONEY_POINT + 1, true)
        )
    }

    @Test
    fun calculateFreeParkingTime_watchMovie_6() {
        Assert.assertEquals(
            FIRST_FREE_TIME + WATCH_MOVIE_FREE_TIME,
            viewModel.calculateFreeParkingMinute(SECOND_MONEY_POINT - 1, true)
        )
    }

    @Test
    fun calculateFreeParkingTime_watchMovie_7() {
        Assert.assertEquals(
            SECOND_FREE_TIME + WATCH_MOVIE_FREE_TIME,
            viewModel.calculateFreeParkingMinute(SECOND_MONEY_POINT, true)
        )
    }

    @Test
    fun calculateFreeParkingTime_watchMovie_8() {
        Assert.assertEquals(
            SECOND_FREE_TIME + WATCH_MOVIE_FREE_TIME,
            viewModel.calculateFreeParkingMinute(SECOND_MONEY_POINT + 1, true)
        )
    }

}