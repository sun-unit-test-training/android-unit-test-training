package com.sun.training.ut.di

import com.sun.training.ut.ui.exercise_nine.ExerciseNineViewModel
import com.sun.training.ut.ui.exercise_one.ExerciseOneViewModel
import com.sun.training.ut.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Declare viewmodel component
 * @param get() is a component given
 */
val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { ExerciseOneViewModel() }
    viewModel { ExerciseNineViewModel() }
}

