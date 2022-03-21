package org.kimp.ptest.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.kimp.ptest.app.adapters.holders.QuestionsRecyclerViewHolder
import org.kimp.ptest.app.serialization.Question
import org.kimp.ptest.databinding.ViewQuestionBinding

class QuestionsRecyclerViewAdapter(private val questions: Map<Int, String>) :
    RecyclerView.Adapter<QuestionsRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsRecyclerViewHolder {
        return QuestionsRecyclerViewHolder(
            ViewQuestionBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            ).root
        )
    }

    override fun onBindViewHolder(holder: QuestionsRecyclerViewHolder, position: Int) {
        holder.questionNumberChip.text = (position + 1).toString()
        holder.questionTextMsg.text = questions[position + 1]
    }

    override fun getItemCount() = questions.keys.size

}