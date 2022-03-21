package org.kimp.ptest.app.serialization

import com.google.gson.annotations.SerializedName

data class QuestionsList(
    @SerializedName("questions") val questions: Array<Question>
)
