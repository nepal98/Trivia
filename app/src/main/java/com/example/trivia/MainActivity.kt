package com.example.trivia

import android.content.Intent
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
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() , View.OnClickListener {
    private var questionBank: QuestionBank = QuestionBank()
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
    private var totalQuestionsCorrect = 0

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

    private fun initialiseViews() {
        questionCounterTextView = findViewById(R.id.questionCounterTextView)
        questionTextView = findViewById(R.id.questionTextView)
        totalCorrectsTextView = findViewById(R.id.totalCorrectTextView)
        trueAnswerButton = findViewById(R.id.trueAnswerButton)
        falseAnswerButton = findViewById(R.id.falseAnswerButton)
        previousQuestionButton = findViewById(R.id.previousQuestionButton)
        nextQuestionButton = findViewById(R.id.nextQuestionButton)
        initialiseButtonListeners()
    }

    private fun initialiseButtonListeners() {
        nextQuestionButton.setOnClickListener(this)
        previousQuestionButton.setOnClickListener(this)
        trueAnswerButton.setOnClickListener(this)
        falseAnswerButton.setOnClickListener(this)
    }

    private fun displayQuestion() {
        questionCounterTextView.text = (getString(R.string.question_counter_text, (questionIndex+1), questionsList.size))
        questionTextView.text = questionsList[questionIndex].toString()
        totalCorrectsTextView.text = (getString(R.string.total_corrects_text, (totalQuestionsCorrect), questionsList.size))
        setNextButtonText()
        Log.d("ANSWER", questionsList[questionIndex].answer.toString())
    }

    private fun isLastQuestion() : Boolean {
        return questionIndex+1 == questionsList.size
    }

    private fun setNextButtonText() {
        nextQuestionButton.text = getString(R.string.next_text)
        if(isLastQuestion()) {
            nextQuestionButton.text = getString(R.string.finish_text)
        }
    }

    private fun nextQuestion() {
        if(isLastQuestion()) {
            loadFinishGameActivity()
        }
        questionIndex = if(questionIndex == questionsList.size-1) questionIndex else ++questionIndex
        displayQuestion()
    }

    private fun loadFinishGameActivity() {
        val intent = Intent(this, FinishGameScreenActivity::class.java)
        intent.putExtra(Constants.USERNAME_KEY, username)
        intent.putExtra(Constants.SCORE_KEY, totalQuestionsCorrect)
        intent.putExtra(Constants.DIFFICULTY_MODE_KEY, gameMode)
        startActivity(intent)
        finish()
    }

    private fun previousQuestion() {
        questionIndex = if(questionIndex == 0) 0 else --questionIndex
        displayQuestion()
    }

    private fun checkAnswer(view: View) {
        val button = view as Button
        val question = questionsList[questionIndex]
        val answer = question.answer.toString()
        if(!question.alreadyAnswered) {
            if(answer.length == button.text.length) {
                totalQuestionsCorrect++
            } else {
                totalQuestionsCorrect = if(totalQuestionsCorrect == 0) 0 else --totalQuestionsCorrect
            }
        } else {
            Snackbar.make(previousQuestionButton, "You have already answered this question!", Snackbar.LENGTH_LONG).show()
        }
        question.alreadyAnswered = true

        nextQuestion()
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.trueAnswerButton, R.id.falseAnswerButton -> checkAnswer(view)
            R.id.previousQuestionButton -> previousQuestion()
            R.id.nextQuestionButton -> nextQuestion()
        }
    }


}