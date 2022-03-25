package org.kimp.ptest.app.adapters.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.chip.Chip
import com.google.android.material.textview.MaterialTextView
import org.kimp.ptest.R

class QuestionsRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val questionTextMsg: MaterialTextView = itemView.findViewById(R.id.qv_question_text_msg)
    val questionNumberChip: Chip = itemView.findViewById(R.id.qv_question_number_chip)

    val questionsToggleGroup: MaterialButtonToggleGroup = itemView.findViewById(R.id.qv_toggle_group)
}
