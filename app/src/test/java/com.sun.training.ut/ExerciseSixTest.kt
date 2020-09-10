package com.sun.training.ut

import com.sun.training.ut.exercise.six.*
import org.junit.Assert
import org.junit.Test

class ExerciseSixTest {

    @Test
    fun calculateFreeParkingTime_test() {
        // not watch movie
        Assert.assertEquals(0, calculateFreeParkingTime(-1, false))
        Assert.assertEquals(0, calculateFreeParkingTime(0, false))
        Assert.assertEquals(0, calculateFreeParkingTime(FIRST_MONEY_POINT - 1, false))
        Assert.assertEquals(FIRST_FREE_TIME, calculateFreeParkingTime(FIRST_MONEY_POINT, false))
        Assert.assertEquals(FIRST_FREE_TIME, calculateFreeParkingTime(FIRST_MONEY_POINT + 1, false))
        Assert.assertEquals(
            FIRST_FREE_TIME,
            calculateFreeParkingTime(SECOND_MONEY_POINT - 1, false)
        )
        Assert.assertEquals(SECOND_FREE_TIME, calculateFreeParkingTime(SECOND_MONEY_POINT, false))
        Assert.assertEquals(
            SECOND_FREE_TIME,
            calculateFreeParkingTime(SECOND_MONEY_POINT + 1, false)
        )

        // watch movie
        Assert.assertEquals(WATCH_MOVIE_FREE_TIME, calculateFreeParkingTime(-1, true))
        Assert.assertEquals(WATCH_MOVIE_FREE_TIME, calculateFreeParkingTime(0, true))
        Assert.assertEquals(
            WATCH_MOVIE_FREE_TIME,
            calculateFreeParkingTime(FIRST_MONEY_POINT - 1, true)
        )
        Assert.assertEquals(
            FIRST_FREE_TIME + WATCH_MOVIE_FREE_TIME,
            calculateFreeParkingTime(FIRST_MONEY_POINT, true)
        )
        Assert.assertEquals(
            FIRST_FREE_TIME + WATCH_MOVIE_FREE_TIME,
            calculateFreeParkingTime(FIRST_MONEY_POINT + 1, true)
        )
        Assert.assertEquals(
            FIRST_FREE_TIME + WATCH_MOVIE_FREE_TIME,
            calculateFreeParkingTime(SECOND_MONEY_POINT - 1, true)
        )
        Assert.assertEquals(
            SECOND_FREE_TIME + WATCH_MOVIE_FREE_TIME,
            calculateFreeParkingTime(SECOND_MONEY_POINT, true)
        )
        Assert.assertEquals(
            SECOND_FREE_TIME + WATCH_MOVIE_FREE_TIME,
            calculateFreeParkingTime(SECOND_MONEY_POINT + 1, true)
        )
    }

}