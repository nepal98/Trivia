package com.example.trivia.model

data class Question(val question: String, val answer: Boolean) {
    override fun toString(): String {
        return question
    }
}