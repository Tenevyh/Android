package com.bignerdranch.android.geomain

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"
private const val Q_INDEX = "index"
private const val REQUEST_CODE_CHEAT= 0

class MainActivity : AppCompatActivity() {

    private lateinit var textSwitcher: TextSwitcher
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var prevButton: ImageButton
    private lateinit var cheatButton: Button

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex
        val questionIndex = savedInstanceState?.getInt(Q_INDEX, 0) ?: 0
        quizViewModel.questionIndex = questionIndex

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        prevButton = findViewById(R.id.prev_button)
        textSwitcher = findViewById(R.id.textSwitcher)
        cheatButton = findViewById(R.id.cheat_button)

        showResult()

        if(quizViewModel.cheatIndex==3){
            cheatButton.setEnabled(false)
        }

        if (quizViewModel.isCompleted()) {
            offButton()
        } else onButton()



        ////////////////////////////////////////////////////

        textSwitcher.setFactory {

            val textView = TextView(this@MainActivity)
            textView.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            textView.setTextColor(Color.WHITE)
            textView

        }

        textSwitcher.setText(quizViewModel.getQuestionBank()[quizViewModel.currentIndex].textResId)

        val textIn = AnimationUtils.loadAnimation(
            this, R.anim.slide_in_right)

        textSwitcher.inAnimation = textIn

        val textOut = AnimationUtils.loadAnimation(
            this, R.anim.slide_out_left)

        textSwitcher.outAnimation = textOut

        val prevTextIn = AnimationUtils.loadAnimation(
            this, android.R.anim.slide_in_left)

        val prevTextOut = AnimationUtils.loadAnimation(
            this, android.R.anim.slide_out_right)


        ////////////////////////////////////////////////////
        textSwitcher.setOnClickListener { view: View ->
            if (quizViewModel.currentIndex == quizViewModel.getQuestionBank().size - 1) {
            } else {
                quizViewModel.currentIndex =
                    (quizViewModel.currentIndex + 1) % quizViewModel.getQuestionBank().size
                updateQuestion()
            }
        }

        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
            quizViewModel.completed()
            offButton()
            if(quizViewModel.questionIndex==quizViewModel.getQuestionBank().size-1){}
            else{quizViewModel.questionIndex++}
            showResult()
            Thread.sleep(250)
            quizViewModel.moveToNext()
            updateQuestion()
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
            quizViewModel.completed()
            offButton()
            if(quizViewModel.questionIndex==quizViewModel.getQuestionBank().size-1){}
            else{quizViewModel.questionIndex++}
            showResult()
            Thread.sleep(1000)
            quizViewModel.moveToNext()
            updateQuestion()
        }

        prevButton.setOnClickListener {
            textSwitcher.inAnimation = prevTextIn
            textSwitcher.outAnimation = prevTextOut
            if (quizViewModel.currentIndex == 0) {
            } else {
                quizViewModel.moveToPrev()
                updateQuestion()
            }
            if (quizViewModel.isCompleted()) {
                offButton()
            } else {onButton()}
            textSwitcher.inAnimation = textIn
            textSwitcher.outAnimation = textOut
        }

        cheatButton.setOnClickListener{
            val answerIsTrue=quizViewModel.currentQuestionAnswer
            val intent= CheatActivity.newIntent(this,answerIsTrue)
            startActivityForResult(intent,REQUEST_CODE_CHEAT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == REQUEST_CODE_CHEAT){
            quizViewModel.cheatQuestion()
        }
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

    override fun onSaveInstanceState(savedInstanceState:  Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
        savedInstanceState.putInt(Q_INDEX, quizViewModel.questionIndex)
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
        textSwitcher.setText(questionTextResId)
        if(quizViewModel.cheatIndex==3){
            cheatButton.setEnabled(false)
        }
        if (quizViewModel.isCompleted()) {
            offButton()
        } else onButton()
    }

    private fun showResult(){
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Результат!")
            .setMessage(" Верно: ${quizViewModel.correctIndex}\n Неверно: ${quizViewModel.inCorrectIndex}\n" +
                    " Чит: ${quizViewModel.cheatIndex}")
        if(quizViewModel.questionIndex== quizViewModel.getQuestionBank().size-1){
            builder.show()

        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        if (quizViewModel.isCheatQuestion()==false) {
            if (userAnswer == correctAnswer) {
                quizViewModel.correctIndex++
            } else {
                quizViewModel.inCorrectIndex++
            }
        } else quizViewModel.cheatIndex++
        val messageResId = when {
            quizViewModel.isCheatQuestion() -> R.string.judgment_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }

        Toast.makeText(this,messageResId, Toast.LENGTH_SHORT).show()
    }

    private fun onButton(){
            trueButton.setEnabled(true)
            falseButton.setEnabled(true)
    }

    private fun offButton(){
            falseButton.setEnabled(false)
            trueButton.setEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.hero_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val fragment = ChooseHero().show(supportFragmentManager, "ChooseHero")
        textSwitcher.setText(quizViewModel.getQuestionBank()[quizViewModel.currentIndex].textResId)
        return true
    }
}