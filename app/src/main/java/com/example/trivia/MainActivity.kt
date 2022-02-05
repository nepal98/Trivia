package com.example.trivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.trivia.data.QuestionBank
import com.example.trivia.constants.Constants
import com.example.trivia.data.QuestionsListAsyncResponse
import com.example.trivia.model.Question

class MainActivity : AppCompatActivity() , View.OnClickListener {
    private lateinit var questionBank: QuestionBank
    private lateinit var questionsList: ArrayList<Question>
    private lateinit var questionCounterTextView: TextView
    private lateinit var questionTextView: TextView
    private lateinit var totalCorrectsTextView: TextView
    private lateinit var trueAnswerButton: Button
    private lateinit var falseAnswerButton: Button
    private lateinit var previousQuestionButton: Button
    private lateinit var nextQuestionButton: Button
    private lateinit var username: String
    private lateinit var gameMode: String
    private var questionIndex = 0

    init {
        questionBank = QuestionBank()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        username = intent.getStringExtra(Constants.USERNAME_KEY).toString()
        gameMode = intent.getStringExtra(Constants.DIFFICULTY_MODE_KEY).toString()
        setContentView(R.layout.activity_main)
        initialiseViews()
        questionsList = questionBank.generateQuestions(gameMode, object: QuestionsListAsyncResponse  {
            override fun processFinished(questionArrayList: java.util.ArrayList<Question>) {
                displayQuestion()
            }
        })
    }

    fun initialiseViews() {
        questionCounterTextView = findViewById(R.id.questionCounterTextView)
        questionTextView = findViewById(R.id.questionTextView)
        totalCorrectsTextView = findViewById(R.id.totalCorrectTextView)
        trueAnswerButton = findViewById(R.id.trueAnswerButton)
        falseAnswerButton = findViewById(R.id.falseAnswerButton)
        previousQuestionButton = findViewById(R.id.previousQuestionButton)
        nextQuestionButton = findViewById(R.id.nextQuestionButton)
        initialiseButtonListeners()
    }

    fun initialiseButtonListeners() {
        nextQuestionButton.setOnClickListener(this)
        previousQuestionButton.setOnClickListener(this)
        trueAnswerButton.setOnClickListener(this)
        falseAnswerButton.setOnClickListener(this)
    }

    fun displayQuestion() {
        questionCounterTextView.text = (getString(R.string.question_counter_text, (questionIndex+1), questionsList.size))
        questionTextView.text = questionsList[questionIndex].toString()
    }

    fun nextQuestion() {
        questionIndex = if(questionIndex == questionsList.size-1) questionIndex else ++questionIndex
        displayQuestion()
    }

    fun previousQuestion() {
        questionIndex = if(questionIndex == 0) 0 else --questionIndex
        displayQuestion()
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.trueAnswerButton -> {}
            R.id.falseAnswerButton -> {}
            R.id.previousQuestionButton -> {previousQuestion()}
            R.id.nextQuestionButton -> {nextQuestion()}
        }
    }


}