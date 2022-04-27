package com.bignerdranch.android.geomain

import android.util.Log
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel(){

    private var questionBank = listOf(
        Question("000000000 ные из лабы 1718.",true),
        Question("Отец Лейлы один из членов ученых лабы 1718.",true),
        Question("Губительную пушку Лейле подарил её дед.",false),
        Question("Лейлу схватили учёные из лабы 1718, потому что она меткий стрелок.",false),
        Question("В похищении Лейлы виновата Губительная Пушка.",true),
        Question("Истинные причины поступка отца, Лейле рассказал дед.",true)
    )

    val questionBankLayla = listOf(
        Question("000000000 ные из лабы 1718.",true),
        Question("Отец Лейлы один из членов ученых лабы 1718.",true),
        Question("Губительную пушку Лейле подарил её дед.",false),
        Question("Лейлу схватили учёные из лабы 1718, потому что она меткий стрелок.",false),
        Question("В похищении Лейлы виновата Губительная Пушка.",true),
        Question("Истинные причины поступка отца, Лейле рассказал дед.",true)
    )

    val questionBankZask = listOf(
        Question("1111111111111.",true),
        Question("1111111111111111111 членов ученых лабы 1718.",true),
        Question("Губительную пушку Лейле подарил её дед.",false),
        Question("Лейлу схватили учёные из лабы 1718, потому что она меткий стрелок.",false),
        Question("В похищении Лейлы виновата Губительная Пушка.",true),
        Question("Истинные причины поступка отца, Лейле рассказал дед.",true)
    )

    val questionBankVanVan = listOf(
        Question("222222222222учёные из лабы 1718.",true),
        Question("22222222222222222222ленов ученых лабы 1718.",true),
        Question("Губительную пушку Лейле подарил её дед.",false),
        Question("Лейлу схватили учёные из лабы 1718, потому что она меткий стрелок.",false),
        Question("В похищении Лейлы виновата Губительная Пушка.",true),
        Question("Истинные причины поступка отца, Лейле рассказал дед.",true)
    )

    val questionBankValir = listOf(
        Question("333333333333 стали учёные из лабы 1718.",true),
        Question("3333333333333333333нов ученых лабы 1718.",true),
        Question("Губительную пушку Лейле подарил её дед.",false),
        Question("Лейлу схватили учёные из лабы 1718, потому что она меткий стрелок.",false),
        Question("В похищении Лейлы виновата Губительная Пушка.",true),
        Question("Истинные причины поступка отца, Лейле рассказал дед.",true)
    )

    var currentIndex = 0
    var questionIndex = 0
    var correctIndex = 0
    var inCorrectIndex = 0
    var cheatIndex = 0
    var showAnswer = false

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText : String
        get() = questionBank[currentIndex].textResId

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrev(){
        currentIndex = (currentIndex - 1) % questionBank.size
    }

    fun getQuestionBank() : List<Question>{
        return questionBank
    }

    fun setQuestionBank(list: List<Question>){
        questionBank = list
    }

    fun completed(){
        questionBank[currentIndex].completed=true
    }

    fun isCompleted(): Boolean{
        return questionBank[currentIndex].completed
    }

    fun cheatQuestion(){
        questionBank[currentIndex].cheat=true
    }

    fun isCheatQuestion(): Boolean{
        return questionBank[currentIndex].cheat
    }
}