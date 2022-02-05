package com.example.trivia.data

import android.app.Application
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.example.trivia.model.Question
import com.example.trivia.controller.AppController

class QuestionBank : Application() {
    private val questionsList: ArrayList<Question> = ArrayList<Question>()
    private val QUESTIONS_URL =
        "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json"
    private var requestHandler: AppController;

    init {
        requestHandler = AppController.getInstance(this)
    }

    fun addQuestion(question: Question) {
        questionsList.add(question)
    }

    fun getQuestion(index: Int): Question? {
        if (index > 0 && index < questionsList.size)
            return questionsList[index]
        return null
    }

    fun getQuestions() : ArrayList<Question>  {
        return questionsList
    }

    fun generateQuestions() {
        val questionsJson = JsonArrayRequest(Request.Method.GET, QUESTIONS_URL, null,
            { response ->
                for(i in 0 until response.length()) {
                    val questionObject = response.getJSONArray(i)
                    val question = questionObject.get(0).toString()
                    val answer = questionObject.getBoolean(1)
                    Log.d("DEBUG", question + "---" + answer)
                    questionsList.add(Question(question, answer))
                }
            },
            { error -> })
        requestHandler.addToRequestQueue(questionsJson)
    }

}