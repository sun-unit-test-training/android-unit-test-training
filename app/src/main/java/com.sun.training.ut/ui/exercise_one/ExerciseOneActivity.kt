package com.sun.training.ut.ui.exercise_one

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.lifecycle.Observer
import com.sun.training.ut.BR
import com.sun.training.ut.R
import com.sun.training.ut.data.model.Beer
import com.sun.training.ut.databinding.ActivityExerciseOneBinding
import com.sun.training.ut.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_exercise_one.*
import org.koin.android.viewmodel.ext.android.viewModel

class ExerciseOneActivity : BaseActivity<ActivityExerciseOneBinding, ExerciseOneViewModel>() {

    override val viewModel: ExerciseOneViewModel by viewModel()
    override val bindingVariable = BR.viewModel
    override val layoutId = R.layout.activity_exercise_one

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.lifecycleOwner = this
    }
}