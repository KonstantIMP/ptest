package org.kimp.ptest.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.get
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.button.MaterialButtonToggleGroup
import org.kimp.ptest.R
import org.kimp.ptest.app.adapters.holders.FinishButtonViewHolder
import org.kimp.ptest.app.adapters.holders.QuestionsRecyclerViewHolder
import org.kimp.ptest.app.models.QuestionsViewModel
import org.kimp.ptest.app.serialization.AnswerType
import org.kimp.ptest.app.serialization.Question
import org.kimp.ptest.databinding.ViewFinishButtonBinding
import org.kimp.ptest.databinding.ViewQuestionBinding

class QuestionsRecyclerViewAdapter(private val questions: Map<Int, String>,
    private val questionsViewModel: QuestionsViewModel, private val lifecycleOwnerRV: LifecycleOwner) :
    RecyclerView.Adapter<ViewHolder>() {

    private val FINISH_BTN_TYPE: Int = 1012
    private val QUESTION_TYPE: Int = 1011

    private var listenersMap: HashMap<Int, MaterialButtonToggleGroup.OnButtonCheckedListener> = HashMap()
    private var isObserverEnabled: Boolean = false

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  {
        if (position + 1 < itemCount) {
            val questionHolder = holder as QuestionsRecyclerViewHolder
            questionHolder.questionNumberChip.text = (position + 1).toString( )
            questionHolder.questionTextMsg.text = questions[position + 1]

            if (!listenersMap.containsKey(position)) {
                listenersMap[position] =
                    MaterialButtonToggleGroup.OnButtonCheckedListener { _, checkedId, isChecked ->
                        if (isChecked) {
                            when (checkedId) {
                                R.id.qv_yes_btn -> questionsViewModel.setAnswer(position, AnswerType.ANSWER_YES)
                                R.id.qv_no_btn -> questionsViewModel.setAnswer(position, AnswerType.ANSWER_NO)
                            }
                        }
                        else questionsViewModel.setAnswer(position, AnswerType.ANSWER_NOT_SET)
                    }
            }

            for (l in listenersMap.values) {
                questionHolder.questionsToggleGroup.removeOnButtonCheckedListener(l)
            }

            when (questionsViewModel.getAnswers().value?.get(position)) {
                AnswerType.ANSWER_YES -> questionHolder.questionsToggleGroup.check(R.id.qv_yes_btn)
                AnswerType.ANSWER_NO -> questionHolder.questionsToggleGroup.check(R.id.qv_no_btn)
                else -> questionHolder.questionsToggleGroup.clearChecked()
            }

            listenersMap[position]?.let {
                questionHolder.questionsToggleGroup.addOnButtonCheckedListener(it)
            }
        }
        else {
            val buttonHolder = holder as FinishButtonViewHolder

            if (!isObserverEnabled) {
                questionsViewModel.getAnswers().observe(lifecycleOwnerRV) {
                    buttonHolder.finishButton.isEnabled = questionsViewModel.isAllAnswered()
                }
                isObserverEnabled = true
            }

            buttonHolder.finishButton.setOnClickListener {

            }
        }
    }

    override fun getItemCount() = questions.keys.size + 1
}