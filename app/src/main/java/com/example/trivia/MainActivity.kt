package com.example.trivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.trivia.data.QuestionBank

class MainActivity : AppCompatActivity() , View.OnClickListener {
    private var questionBank: QuestionBank = QuestionBank()
    private lateinit var questionCounterTextView: TextView
    private lateinit var questionTextView: TextView
    private lateinit var totalCorrectsTextView: TextView
    private lateinit var trueAnswerButton: Button
    private lateinit var falseAnswerButton: Button
    private lateinit var previousQuestionButton: Button
    private lateinit var nextQuestionButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        questionBank.generateQuestions()
        initialiseViews()
    }

    fun initialiseViews() {
        questionCounterTextView = findViewById(R.id.questionCounterTextView)
        questionTextView = findViewById(R.id.questionTextView)
        totalCorrectsTextView = findViewById(R.id.totalCorrectTextView)
        trueAnswerButton = findViewById(R.id.trueAnswerButton)
        falseAnswerButton = findViewById(R.id.falseAnswerButton)
        previousQuestionButton = findViewById(R.id.previousQuestionButton)
        nextQuestionButton = findViewById(R.id.nextQuestionButton)
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.trueAnswerButton -> {}
            R.id.falseAnswerButton -> {}
            R.id.previousQuestionButton -> {}
            R.id.nextQuestionButton -> {}
        }
    }


}