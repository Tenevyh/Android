package com.bignerdranch.android.geomain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

const val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.question_text_view)

        offButton()
        onButton()

            trueButton.setOnClickListener { view: View ->
                checkAnswer(true)
                trueButton.setEnabled(false)
                falseButton.setEnabled(false)
                quizViewModel.questionIndex++
                showResult()
            }

            questionTextView.setOnClickListener { view: View ->
                if (quizViewModel.currentIndex == quizViewModel.getQuestionBank().size - 1) {
                } else {
                    quizViewModel.currentIndex = (quizViewModel.currentIndex + 1) % quizViewModel.getQuestionBank().size
                    updateQuestion()
                }
            }

            falseButton.setOnClickListener { view: View ->
                checkAnswer(false)
                falseButton.setEnabled(false)
                trueButton.setEnabled(false)
                quizViewModel.questionIndex++
                showResult()
            }

            prevButton.setOnClickListener {
                if (quizViewModel.currentIndex == 0) {
                } else {
                    quizViewModel.moveToPrev()
                    updateQuestion()
                }
                offButton()
            }

            nextButton.setOnClickListener {
                if (quizViewModel.currentIndex == quizViewModel.getQuestionBank().size - 1) {
                } else {
                    quizViewModel.moveToNext()
                    updateQuestion()
                }
                onButton()
        }

            val questionTextResId = quizViewModel.getQuestionBank()[quizViewModel.currentIndex].textResId
            questionTextView.setText(questionTextResId)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause(){
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        if (userAnswer == correctAnswer){
            quizViewModel.correctIndex++
        } else {
            quizViewModel.inCorrectIndex++
        }
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this,messageResId, Toast.LENGTH_SHORT).show()
    }

    private fun showResult(){
        var result ="True: ${quizViewModel.correctIndex}, False: ${quizViewModel.inCorrectIndex}"
        if(quizViewModel.questionIndex== quizViewModel.getQuestionBank().size){
            Toast.makeText(this,result, Toast.LENGTH_SHORT).show()
        }
    }

    private fun onButton(){
        if (quizViewModel.questionIndex<=quizViewModel.currentIndex) {
            trueButton.setEnabled(true)
            falseButton.setEnabled(true)
        }
    }

    private fun offButton(){
        if (quizViewModel.questionIndex>quizViewModel.currentIndex) {
            falseButton.setEnabled(false)
            trueButton.setEnabled(false)
        }
    }
}