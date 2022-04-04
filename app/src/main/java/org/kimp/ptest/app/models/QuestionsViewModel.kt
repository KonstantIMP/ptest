package org.kimp.ptest.app.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.GsonBuilder
import java.io.InputStream
import java.util.Arrays
import java.util.LinkedList
import java.util.function.Function
import java.util.stream.Collectors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kimp.ptest.app.serialization.AnswerType
import org.kimp.ptest.app.serialization.QuestionsList
import kotlin.streams.toList

class QuestionsViewModel : ViewModel() {
    private val questions: MutableLiveData<Map<Int, String>> = MutableLiveData()
    private val answers: MutableLiveData<List<AnswerType>> = MutableLiveData()

    init {
        val answersArray = ArrayList<AnswerType>()
        for (i in 0 .. 56) answersArray.add(AnswerType.ANSWER_NOT_SET)
        answers.value = answersArray
    }

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

    fun getAnswers(): LiveData<List<AnswerType>> = answers

    fun setAnswer(index: Int, answer: AnswerType) {
        val answersArray: ArrayList<AnswerType> = answers.value as ArrayList<AnswerType>
        answersArray[index] = answer
        answers.value = answersArray
    }

    fun isAllAnswered() : Boolean {
        for (i in answers.value!!) {
            if (i == AnswerType.ANSWER_NOT_SET) return false
        }
        return true
    }
}