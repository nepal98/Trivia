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

    fun getQuestion(index: Int): Question? {
        if (index > 0 && index < questionsList.size)
            return questionsList[index]
        return null
    }

    fun generateQuestions(difficultyMode: String, callback: QuestionsListAsyncResponse) : ArrayList<Question> {
        totalQuestionsToGenerate = when(difficultyMode) {
            "Easy" -> 10
            "Medium" -> 20
            "Expert" -> 30
            "Genius" -> 50
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