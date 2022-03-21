package org.kimp.ptest.app.adapters.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.google.android.material.textview.MaterialTextView
import org.kimp.ptest.R

class QuestionsRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val questionTextMsg: MaterialTextView = itemView.findViewById(R.id.qv_question_text_msg)
    val questionNumberChip: Chip = itemView.findViewById(R.id.qv_question_number_chip)

    val yesBtn: MaterialButton = itemView.findViewById(R.id.qv_yes_btn)
    val noBtn: MaterialButton = itemView.findViewById(R.id.qv_no_btn)
}
