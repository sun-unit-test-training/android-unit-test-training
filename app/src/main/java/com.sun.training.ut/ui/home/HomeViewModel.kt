package com.sun.training.ut.ui.home

import androidx.lifecycle.MutableLiveData
import com.sun.training.ut.data.model.Post
import com.sun.training.ut.data.repository.SampleRepository
import com.sun.training.ut.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.util.Log

class HomeViewModel(private val repository: SampleRepository) : BaseViewModel()