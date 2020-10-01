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
    fun calculateFreeParkingTime_noWatchMovie_negativeMoney() {
        viewModel.totalPurchased = -1
        viewModel.onWatchMovieChecked(false)
        viewModel.calculateMinute()
        Assert.assertEquals(0, viewModel.freeParkingInMinute.value)
    }

    @Test
    fun calculateFreeParkingTime_noWatchMovie_zeroMoney() {
        viewModel.totalPurchased = 0
        viewModel.onWatchMovieChecked(false)
        viewModel.calculateMinute()
        Assert.assertEquals(0, viewModel.freeParkingInMinute.value)
    }

    @Test
    fun calculateFreeParkingTime_noWatchMovie_underFirstMoneyPoint() {
        viewModel.totalPurchased = FIRST_MONEY_POINT - 1
        viewModel.onWatchMovieChecked(false)
        viewModel.calculateMinute()
        Assert.assertEquals(0, viewModel.freeParkingInMinute.value)
    }

    @Test
    fun calculateFreeParkingTime_noWatchMovie_firstMoneyPoint() {
        viewModel.totalPurchased = FIRST_MONEY_POINT
        viewModel.onWatchMovieChecked(false)
        viewModel.calculateMinute()
        Assert.assertEquals(FIRST_FREE_TIME, viewModel.freeParkingInMinute.value)
    }

    @Test
    fun calculateFreeParkingTime_noWatchMovie_aboveFirstMoneyPoint() {
        viewModel.totalPurchased = FIRST_MONEY_POINT + 1
        viewModel.onWatchMovieChecked(false)
        viewModel.calculateMinute()
        Assert.assertEquals(FIRST_FREE_TIME, viewModel.freeParkingInMinute.value)
    }

    @Test
    fun calculateFreeParkingTime_noWatchMovie_underSecondMoneyPoint() {
        viewModel.totalPurchased = SECOND_MONEY_POINT - 1
        viewModel.onWatchMovieChecked(false)
        viewModel.calculateMinute()
        Assert.assertEquals(FIRST_FREE_TIME, viewModel.freeParkingInMinute.value)
    }

    @Test
    fun calculateFreeParkingTime_noWatchMovie_secondMoneyPoint() {
        viewModel.totalPurchased = SECOND_MONEY_POINT
        viewModel.onWatchMovieChecked(false)
        viewModel.calculateMinute()
        Assert.assertEquals(SECOND_FREE_TIME, viewModel.freeParkingInMinute.value)
    }

    @Test
    fun calculateFreeParkingTime_noWatchMovie_aboveSecondMoneyPoint() {
        viewModel.totalPurchased = SECOND_MONEY_POINT + 1
        viewModel.onWatchMovieChecked(false)
        viewModel.calculateMinute()
        Assert.assertEquals(SECOND_FREE_TIME, viewModel.freeParkingInMinute.value)
    }

    @Test
    fun calculateFreeParkingTime_watchMovie_negativeMoney() {
        viewModel.totalPurchased = -1
        viewModel.onWatchMovieChecked(true)
        viewModel.calculateMinute()
        Assert.assertEquals(WATCH_MOVIE_FREE_TIME, viewModel.freeParkingInMinute.value)
    }

    @Test
    fun calculateFreeParkingTime_watchMovie_zeroMoney() {
        viewModel.totalPurchased = 0
        viewModel.onWatchMovieChecked(true)
        viewModel.calculateMinute()
        Assert.assertEquals(WATCH_MOVIE_FREE_TIME, viewModel.freeParkingInMinute.value)
    }

    @Test
    fun calculateFreeParkingTime_watchMovie_underFirstMoneyPoint() {
        viewModel.totalPurchased = FIRST_MONEY_POINT - 1
        viewModel.onWatchMovieChecked(true)
        viewModel.calculateMinute()
        Assert.assertEquals(WATCH_MOVIE_FREE_TIME, viewModel.freeParkingInMinute.value)
    }

    @Test
    fun calculateFreeParkingTime_watchMovie_firstMoneyPoint() {
        viewModel.totalPurchased = FIRST_MONEY_POINT
        viewModel.onWatchMovieChecked(true)
        viewModel.calculateMinute()
        Assert.assertEquals(FIRST_FREE_TIME + WATCH_MOVIE_FREE_TIME, viewModel.freeParkingInMinute.value)
    }

    @Test
    fun calculateFreeParkingTime_watchMovie_aboveFirstMoneyPoint() {
        viewModel.totalPurchased = FIRST_MONEY_POINT + 1
        viewModel.onWatchMovieChecked(true)
        viewModel.calculateMinute()
        Assert.assertEquals(FIRST_FREE_TIME + WATCH_MOVIE_FREE_TIME, viewModel.freeParkingInMinute.value)
    }

    @Test
    fun calculateFreeParkingTime_watchMovie_underSecondMoneyPoint() {
        viewModel.totalPurchased = SECOND_MONEY_POINT - 1
        viewModel.onWatchMovieChecked(true)
        viewModel.calculateMinute()
        Assert.assertEquals(FIRST_FREE_TIME + WATCH_MOVIE_FREE_TIME, viewModel.freeParkingInMinute.value)
    }

    @Test
    fun calculateFreeParkingTime_watchMovie_secondMoneyPoint() {
        viewModel.totalPurchased = SECOND_MONEY_POINT
        viewModel.onWatchMovieChecked(true)
        viewModel.calculateMinute()
        Assert.assertEquals(SECOND_FREE_TIME + WATCH_MOVIE_FREE_TIME, viewModel.freeParkingInMinute.value)
    }

    @Test
    fun calculateFreeParkingTime_watchMovie_aboveSecondMoneyPoint() {
        viewModel.totalPurchased = SECOND_MONEY_POINT + 1
        viewModel.onWatchMovieChecked(true)
        viewModel.calculateMinute()
        Assert.assertEquals(SECOND_FREE_TIME + WATCH_MOVIE_FREE_TIME, viewModel.freeParkingInMinute.value)
    }

}