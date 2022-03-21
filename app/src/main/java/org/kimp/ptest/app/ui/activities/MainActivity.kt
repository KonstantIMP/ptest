package org.kimp.ptest.app.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.kimp.ptest.R
import org.kimp.ptest.app.models.QuestionsViewModel
import org.kimp.ptest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val questionsViewModel = ViewModelProvider(this)[QuestionsViewModel::class.java]
        questionsViewModel.loadQuestions(resources.openRawResource(R.raw.questions))
    }
}
