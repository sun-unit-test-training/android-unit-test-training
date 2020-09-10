package com.sun.training.ut

import com.sun.training.ut.exercise.six.*
import org.junit.Assert
import org.junit.Test

class ExerciseSixTest {

    @Test
    fun calculateFreeParkingTime_test1() {
        Assert.assertEquals(0, calculateFreeParkingTime(-1, false))
    }

    @Test
    fun calculateFreeParkingTime_test2() {
        Assert.assertEquals(0, calculateFreeParkingTime(0, false))
    }

    @Test
    fun calculateFreeParkingTime_test3() {
        Assert.assertEquals(0, calculateFreeParkingTime(FIRST_MONEY_POINT - 1, false))
    }

    @Test
    fun calculateFreeParkingTime_test4() {
        Assert.assertEquals(FIRST_FREE_TIME, calculateFreeParkingTime(FIRST_MONEY_POINT, false))
    }

    @Test
    fun calculateFreeParkingTime_test5() {
        Assert.assertEquals(FIRST_FREE_TIME, calculateFreeParkingTime(FIRST_MONEY_POINT + 1, false))
    }

    @Test
    fun calculateFreeParkingTime_test6() {
        Assert.assertEquals(
            FIRST_FREE_TIME,
            calculateFreeParkingTime(SECOND_MONEY_POINT - 1, false)
        )
    }

    @Test
    fun calculateFreeParkingTime_test7() {
        Assert.assertEquals(SECOND_FREE_TIME, calculateFreeParkingTime(SECOND_MONEY_POINT, false))
    }

    @Test
    fun calculateFreeParkingTime_test8() {
        Assert.assertEquals(
            SECOND_FREE_TIME,
            calculateFreeParkingTime(SECOND_MONEY_POINT + 1, false)
        )
    }

    @Test
    fun calculateFreeParkingTime_test9() {
        Assert.assertEquals(WATCH_MOVIE_FREE_TIME, calculateFreeParkingTime(-1, true))
    }

    @Test
    fun calculateFreeParkingTime_test10() {
        Assert.assertEquals(WATCH_MOVIE_FREE_TIME, calculateFreeParkingTime(0, true))
    }

    @Test
    fun calculateFreeParkingTime_test11() {
        Assert.assertEquals(
            WATCH_MOVIE_FREE_TIME,
            calculateFreeParkingTime(FIRST_MONEY_POINT - 1, true)
        )
    }

    @Test
    fun calculateFreeParkingTime_test12() {
        Assert.assertEquals(
            FIRST_FREE_TIME + WATCH_MOVIE_FREE_TIME,
            calculateFreeParkingTime(FIRST_MONEY_POINT, true)
        )
    }

    @Test
    fun calculateFreeParkingTime_test13() {
        Assert.assertEquals(
            FIRST_FREE_TIME + WATCH_MOVIE_FREE_TIME,
            calculateFreeParkingTime(FIRST_MONEY_POINT + 1, true)
        )
    }

    @Test
    fun calculateFreeParkingTime_test15() {
        Assert.assertEquals(
            FIRST_FREE_TIME + WATCH_MOVIE_FREE_TIME,
            calculateFreeParkingTime(SECOND_MONEY_POINT - 1, true)
        )
    }

    @Test
    fun calculateFreeParkingTime_test16() {
        Assert.assertEquals(
            SECOND_FREE_TIME + WATCH_MOVIE_FREE_TIME,
            calculateFreeParkingTime(SECOND_MONEY_POINT, true)
        )
    }

    @Test
    fun calculateFreeParkingTime_test17() {
        Assert.assertEquals(
            SECOND_FREE_TIME + WATCH_MOVIE_FREE_TIME,
            calculateFreeParkingTime(SECOND_MONEY_POINT + 1, true)
        )
    }

}