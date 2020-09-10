package com.sun.training.ut.ui.exercise_nine

import android.os.Bundle
import androidx.lifecycle.Observer
import com.sun.training.ut.BR
import com.sun.training.ut.R
import com.sun.training.ut.databinding.ActivityExerciseNineBinding
import com.sun.training.ut.ui.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class ExerciseNineActivity : BaseActivity<ActivityExerciseNineBinding, ExerciseNineViewModel>() {

    override val viewModel: ExerciseNineViewModel by viewModel()
    override val bindingVariable = BR.viewModel
    override val layoutId = R.layout.activity_exercise_nine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.lifecycleOwner = this
        viewModel.resultBeatBoss.observe(this, Observer { result ->
            val textResult = if (!result.findRoom) {
                resources.getString(R.string.can_not_find_room)
            } else if (result.inputRoom != true) {
                resources.getString(R.string.can_not_go_room)
            } else if (result.beatBoss != true) {
                resources.getString(R.string.can_not_beat_boss)
            } else {
                resources.getString(R.string.beat_boss)
            }
            viewModel.updateTextResultBeatBoss(textResult)
        })
    }
}
