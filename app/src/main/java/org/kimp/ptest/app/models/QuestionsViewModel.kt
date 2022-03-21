package org.kimp.ptest.app.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import java.io.InputStream
import java.util.Arrays
import java.util.function.Function
import java.util.stream.Collectors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kimp.ptest.app.serialization.QuestionsList
import kotlin.streams.toList

class QuestionsViewModel : ViewModel() {
    private val questions: MutableLiveData<Map<Int, String>> = MutableLiveData()

    fun loadQuestions(inputStream: InputStream) {
        viewModelScope.launch(Dispatchers.IO) {
            questions.postValue(
                GsonBuilder()
                    .create()
                    .fromJson(
                        inputStream.bufferedReader().use { it.readText() },
                        QuestionsList::class.java
                    ).questions.associateBy ( {it.number}, {it.text} )
            )
        }
    }

    fun getQuestions(): LiveData<Map<Int, String>> = questions
}