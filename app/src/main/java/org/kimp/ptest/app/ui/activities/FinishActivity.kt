package org.kimp.ptest.app.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.kimp.ptest.R
import org.kimp.ptest.app.models.QuestionsViewModel
import org.kimp.ptest.app.serialization.AnswerType
import org.kimp.ptest.databinding.ActivityFinishBinding
import org.kimp.ptest.databinding.ViewFinishTableRowBinding

class FinishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val answers = intent.getBooleanArrayExtra("answers")!!

        val middle = answers.size / 2 + answers.size % 2

        for (i in 0 until middle) {
            val row = ViewFinishTableRowBinding.inflate(layoutInflater)

            row.ftrLeftNum.text = (i + 1).toString()
            row.ftrLeftAns.text = if (answers[i]) resources.getString(R.string.qv_yes_btn)
                else resources.getString(R.string.qv_no_btn)

            if (i + middle < answers.size) {
                row.ftrRightNum.text = (i + middle + 1).toString()
                row.ftrRightAns.text = if (answers[i + middle]) resources.getString(R.string.qv_yes_btn)
                    else resources.getString(R.string.qv_no_btn)
            }

            if (i + 1 == middle) {
                row.ftrLeftNum.background = resources.getDrawable(R.drawable.bottom_left_table_shape, theme)
                row.ftrRightAns.background = resources.getDrawable(R.drawable.bottom_right_table_shape, theme)
            }

            binding.faAnswersTable.addView(row.root, i + 1)
        }
    }
}