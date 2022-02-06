package com.example.trivia.data

import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.example.trivia.model.Question
import com.example.trivia.controller.AppController

class QuestionBank {
    private val questionsList: ArrayList<Question> = ArrayList<Question>()
    private val QUESTIONS_URL =
        "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json"
    private var totalQuestionsToGenerate = 0

    fun generateQuestions(difficultyMode: String, callback: QuestionsListAsyncResponse) : ArrayList<Question> {
        totalQuestionsToGenerate = when(difficultyMode) {
            "Easy" -> 25
            "Medium" -> 50
            "Expert" -> 75
            "Genius" -> 100
            else -> 10
        }
        val questionsJson = JsonArrayRequest(Request.Method.GET, QUESTIONS_URL, null,
            { response ->
                for(i in 0 until totalQuestionsToGenerate) {
                    val questionObject = response.getJSONArray(i)
                    val question = questionObject.get(0).toString()
                    val answer = questionObject.getBoolean(1)
                    questionsList.add(Question(question, answer))
                }
                callback.processFinished(questionsList)
            },
            { error -> })
        AppController.getInstance().addToRequestQueue(questionsJson)
        return questionsList
    }

}