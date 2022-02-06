package com.example.trivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.trivia.constants.Constants

class FinishGameScreenActivity : AppCompatActivity() {
    private lateinit var usernameTextView: TextView
    private lateinit var resultTextView: TextView
    private lateinit var username: String
    private lateinit var difficultyMode: String
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_game_screen)
        username = intent.getStringExtra(Constants.USERNAME_KEY).toString()
        score = intent.getIntExtra(Constants.SCORE_KEY, 0)
        difficultyMode = intent.getStringExtra(Constants.DIFFICULTY_MODE_KEY).toString()
        initialiseViews()

    }

    private fun initialiseViews() {
        usernameTextView = findViewById<TextView>(R.id.congratsNameTextView).apply {
            text = getString(R.string.congrats_text, username)
        }
        resultTextView = findViewById<TextView>(R.id.resultTextView).apply {
            text = getString(R.string.result_text, score, difficultyMode)
        }
    }


}