package com.project.android.legend

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
import com.bignerdranch.android.geomain.R

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"
private const val Q_INDEX = "index"
private const val REQUEST_CODE_CHEAT= 0

class MainActivity : AppCompatActivity(), SelectedHero {

    private lateinit var textSwitcher: TextSwitcher
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var prevButton: ImageButton
    private lateinit var cheatButton: Button

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this)[QuizViewModel::class.java]
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

        if(quizViewModel.cheatIndex==3){
            cheatButton.isEnabled = false
        }

        if (quizViewModel.isCompleted()) {
            offButton()
        } else onButton()

        if (quizViewModel.getQuestionBank().size < 3){
            offButton()
            prevButton.isEnabled  = false
            textSwitcher.isEnabled = false
        }



        ////////////////////////////////////////////////////

        textSwitcher.setFactory {

            val textView = TextView(this@MainActivity)
            textView.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            textView.setTextColor(Color.WHITE)
            textView

        }

        textSwitcher.setText(quizViewModel.getQuestionBank()[quizViewModel.currentIndex].textResId)

        val textIn = AnimationUtils.loadAnimation(
            this, R.anim.slide_in_right
        )

        textSwitcher.inAnimation = textIn

        val textOut = AnimationUtils.loadAnimation(
            this, R.anim.slide_out_left
        )

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
            val intent= CheatActivity.newIntent(this, answerIsTrue)
            startActivityForResult(intent, REQUEST_CODE_CHEAT)
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
        if(quizViewModel.getQuestionBank().size < 3) {
            val fragment = ChooseHero().show(supportFragmentManager, "ChooseHero")
        }
        super.onStart()
    }

    override fun onSaveInstanceState(savedInstanceState:  Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
        savedInstanceState.putInt(Q_INDEX, quizViewModel.questionIndex)
    }

    private fun showResult(){
        if (quizViewModel.questionIndex == quizViewModel.getQuestionBank().size - 1) {
        var builder = AlertDialog.Builder(this)
            builder.setTitle("Результат!").setMessage(
                " Верно: ${quizViewModel.correctIndex}\n " +
                        "Неверно: ${quizViewModel.inCorrectIndex}\n" +
                        " Чит: ${quizViewModel.cheatIndex}"
                )
                builder.show()
        }
    }

    private fun onButton(){
        trueButton.isEnabled = true
        falseButton.isEnabled = true
    }

    private fun offButton(){
        falseButton.isEnabled = false
        trueButton.isEnabled = false
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

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        if (!quizViewModel.isCheatQuestion()) {
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.hero_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val fragment = ChooseHero().show(supportFragmentManager, "ChooseHero")
        return true
    }

    override fun clickHero(hero: String) {
        val rootLayout: LinearLayout = findViewById(R.id.question)
        when (hero){
            "0" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[0].question)
                rootLayout.setBackgroundResource(R.drawable.lela)}
            "1" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[1].question)
                rootLayout.setBackgroundResource(R.drawable.zhask)}
            "2" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[2].question)
                rootLayout.setBackgroundResource(R.drawable.vanvan)}
            "3" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[3].question)
                rootLayout.setBackgroundResource(R.drawable.valir)}
            "4" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[4].question)
                rootLayout.setBackgroundResource(R.drawable.beatris)}
            "5" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[4].question)
                rootLayout.setBackgroundResource(R.drawable.beatris)}
            "6" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[4].question)
                rootLayout.setBackgroundResource(R.drawable.beatris)}
            "7" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[4].question)
                rootLayout.setBackgroundResource(R.drawable.beatris)}
            "8" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[4].question)
                rootLayout.setBackgroundResource(R.drawable.beatris)}
            "9" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[4].question)
                rootLayout.setBackgroundResource(R.drawable.beatris)}
            "10" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[4].question)
                rootLayout.setBackgroundResource(R.drawable.beatris)}
            "11" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[4].question)
                rootLayout.setBackgroundResource(R.drawable.beatris)}
            "12" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[4].question)
                rootLayout.setBackgroundResource(R.drawable.beatris)}
            "13" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[4].question)
                rootLayout.setBackgroundResource(R.drawable.beatris)}
            "14" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[4].question)
                rootLayout.setBackgroundResource(R.drawable.beatris)}
            "15" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[4].question)
                rootLayout.setBackgroundResource(R.drawable.beatris)}
            "16" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[4].question)
                rootLayout.setBackgroundResource(R.drawable.beatris)}
            "17" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[4].question)
                rootLayout.setBackgroundResource(R.drawable.beatris)}
            "18" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[4].question)
                rootLayout.setBackgroundResource(R.drawable.beatris)}
            "19" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[4].question)
                rootLayout.setBackgroundResource(R.drawable.beatris)}
            "20" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[4].question)
                rootLayout.setBackgroundResource(R.drawable.beatris)}
            "21" -> {quizViewModel.setQuestionBank(quizViewModel.getHero()[4].question)
                rootLayout.setBackgroundResource(R.drawable.beatris)}

        }
        quizViewModel.clearResult()
        updateQuestion()
    }
}