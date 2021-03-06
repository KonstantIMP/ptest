package org.kimp.ptest.app.serialization

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("number") val number: Int,
    @SerializedName("text") val text: String
)
