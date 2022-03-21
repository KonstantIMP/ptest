package org.kimp.ptest.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.kimp.ptest.app.adapters.holders.FinishButtonViewHolder
import org.kimp.ptest.app.adapters.holders.QuestionsRecyclerViewHolder
import org.kimp.ptest.app.serialization.Question
import org.kimp.ptest.databinding.ViewFinishButtonBinding
import org.kimp.ptest.databinding.ViewQuestionBinding

class QuestionsRecyclerViewAdapter(private val questions: Map<Int, String>) :
    RecyclerView.Adapter<ViewHolder>() {

    private val FINISH_BTN_TYPE: Int = 1012
    private val QUESTION_TYPE: Int = 1011

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) FINISH_BTN_TYPE
        else QUESTION_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == FINISH_BTN_TYPE) {
            FinishButtonViewHolder(
                ViewFinishButtonBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ),
                    parent,
                    false
                ).root
            )
        } else QuestionsRecyclerViewHolder(
            ViewQuestionBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            ).root
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position + 1 < itemCount) {
            val questionHolder = holder as QuestionsRecyclerViewHolder
            questionHolder.questionNumberChip.text = (position + 1).toString()
            questionHolder.questionTextMsg.text = questions[position + 1]
        }
    }

    override fun getItemCount() = questions.keys.size + 1

}