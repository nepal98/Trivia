package com.example.trivia.data

import com.example.trivia.model.Question
import java.util.ArrayList

interface QuestionsListAsyncResponse {
    fun processFinished(questionArrayList: ArrayList<Question>)
}