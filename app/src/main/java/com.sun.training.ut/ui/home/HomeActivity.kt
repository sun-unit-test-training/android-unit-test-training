package com.sun.training.ut.ui.home

import android.content.Intent
import android.os.Bundle
import com.sun.training.ut.BR
import com.sun.training.ut.R
import com.sun.training.ut.data.model.Exercise
import com.sun.training.ut.databinding.ActivityHomeBinding
import com.sun.training.ut.ui.base.BaseActivity
import com.sun.training.ut.ui.exercise_nine.ExerciseNineActivity
import com.sun.training.ut.ui.exercise_one.ExerciseOneActivity
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Sample activity
 * Show list post got from URL_END_POINT = https://jsonplaceholder.typicode.com/
 */
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeItemClickListener {
    override val viewModel: HomeViewModel by viewModel()
    override val bindingVariable = BR.viewModel
    override val layoutId = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpAdapter()
    }

    private fun setUpAdapter() {
        viewBinding.adapter = HomeAdapter(this).apply {
            submitList(
                listOf(
                    Exercise("Ex1: Bia hơi Keangnam", ExerciseOneActivity::class.java),
                    Exercise("Ex2: ATM của ngân hàng Việt Nam", this@HomeActivity.javaClass),
                    Exercise("Ex3: Quần áo nam trên Hoàn Kiếm", this@HomeActivity.javaClass),
                    Exercise("Ex4: Mr.A đang làm 1 app về calendar", this@HomeActivity.javaClass),
                    Exercise("Ex5: 8 pieces pizza", this@HomeActivity.javaClass),
                    Exercise("Ex6: Trung tâm mua sắm Tây Hồ", this@HomeActivity.javaClass),
                    Exercise("Ex7: Vietnam Mart", this@HomeActivity.javaClass),
                    Exercise(
                        "Ex8: Mr. A đến một sân chơi nọ để chơi cầu lông",
                        this@HomeActivity.javaClass
                    ),
                    Exercise("Ex9: Roll Playng Game Hanoi quest", ExerciseNineActivity::class.java),
                    Exercise("Ex10: Nhà hàng Nam Từ Liêm", this@HomeActivity.javaClass)
                )
            )
        }
    }

    override fun onHomeItemClicked(item: Exercise<*>) {
        startActivity(Intent(this, item.clz))
    }
}
