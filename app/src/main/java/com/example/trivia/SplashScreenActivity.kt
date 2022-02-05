package com.example.trivia

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import com.example.trivia.constants.Constants
import com.google.android.material.snackbar.Snackbar



@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var usernameEditText: EditText
    private lateinit var easyLevelRadioButton: RadioButton
    private lateinit var mediumLevelRadioButton: RadioButton
    private lateinit var expertLevelRadioButton: RadioButton
    private lateinit var geniusLevelRadioButton: RadioButton
    private lateinit var startGameButton: Button
    private var DIFFICULTY_MODE = "Easy"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        initialiseViews()
    }

    private fun initialiseViews() {
        usernameEditText = findViewById(R.id.usernameEditText)
        easyLevelRadioButton = findViewById(R.id.easyRadioGroupButton)
        mediumLevelRadioButton = findViewById(R.id.mediumRadioGroupButton)
        expertLevelRadioButton = findViewById(R.id.expertRadioGroupButton)
        geniusLevelRadioButton = findViewById(R.id.geniusRadioGroupButton)
        startGameButton = findViewById(R.id.startGameButton)
        initialiseButtonListeners()
    }

    private fun initialiseButtonListeners() {
        startGameButton.setOnClickListener(this)
        easyLevelRadioButton.setOnClickListener(this)
        mediumLevelRadioButton.setOnClickListener(this)
        expertLevelRadioButton.setOnClickListener(this)
        geniusLevelRadioButton.setOnClickListener(this)
    }

    private fun validateUsername(): Boolean {
        val usernameEntered = usernameEditText.text.trim()
        if(usernameEntered.isEmpty()) {
            Snackbar.make(usernameEditText, R.string.invalid_username_text, Snackbar.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun startGame() {
        if(validateUsername()) {
            loadMainScreen()
        }
    }

    private fun loadMainScreen() {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(Constants.USERNAME_KEY, "${usernameEditText.text}")
            putExtra(Constants.DIFFICULTY_MODE_KEY, DIFFICULTY_MODE)
        }
        finish()
        startActivity(intent)
    }

    override fun onClick(view: View) {
        if(view is RadioButton) {
            DIFFICULTY_MODE = view.text.toString()
        } else if(view is Button) {
            startGame()
        }
    }
}
