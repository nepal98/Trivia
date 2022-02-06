package com.example.trivia.model

data class Question(val question: String, val answer: Boolean) {
    var alreadyAnswered = false
    override fun toString(): String {
        return question
    }
}