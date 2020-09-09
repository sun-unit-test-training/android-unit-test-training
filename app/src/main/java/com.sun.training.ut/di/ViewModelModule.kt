package com.sun.training.ut.di

import com.sun.training.ut.ui.sample.SampleViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Declare viewmodel component
 * @param get() is a component given
 */
val viewModelModule = module {
    viewModel { SampleViewModel(get()) }
}

