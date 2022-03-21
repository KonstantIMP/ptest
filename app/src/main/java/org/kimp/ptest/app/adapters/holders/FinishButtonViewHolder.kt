package org.kimp.ptest.app.adapters.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import org.kimp.ptest.R

class FinishButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val finishButton: MaterialButton = itemView.findViewById(R.id.fbv_finish_btn)
}
