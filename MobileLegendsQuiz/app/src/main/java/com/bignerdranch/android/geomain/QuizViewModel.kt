package com.bignerdranch.android.geomain

import android.util.Log
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel(){

    private var questionBank = listOf(
        Question("Противниками Лейлы стали ученые из лабы 1718.",true),
        Question("Отец Лейлы один из членов ученых лабы 1718.",true),
        Question("Губительную пушку Лейле подарил её дед.",false),
        Question("Лейлу схватили учёные из лабы 1718, потому что она меткий стрелок.",false),
        Question("В похищении Лейлы виновата Губительная Пушка.",true),
        Question("Истинные причины поступка отца, Лейле рассказал дед.",true)
    )

    val questionBankLayla = listOf(
        Question("Противниками Лейлы стали ученые из лабы 1718.",true),
        Question("Отец Лейлы один из членов ученых лабы 1718.",true),
        Question("Губительную пушку Лейле подарил её дед.",false),
        Question("Лейлу схватили учёные из лабы 1718, потому что она меткий стрелок.",false),
        Question("В похищении Лейлы виновата Губительная Пушка.",true),
        Question("Истинные причины поступка отца, Лейле рассказал дед.",true)
    )

    val questionBankZask = listOf(
        Question("Заск король Роя Кастии.",true),
        Question("Заклятый враг Роя Кастии - Митлора.",true),
        Question("Охотник из Митлоры уничтожил Рой Кастии.",false),
        Question("Кастия уцелела после нападения охотника из Милторы.",false),
        Question("Своё вторжения в Земли Рассвета, Заск начал с северной долины.",true),
        Question("Именно Авроре удалось сильно ранить Заска.",true)
    )

    val questionBankVanVan = listOf(
        Question("Иглы Ван Ван это наследие Людей Тана.",true),
        Question("Судьбу Ван Ван навсегда изменил Чёрный Дракон.",true),
        Question("Чёрный дракон долго и упорно обучал Ван Ван.",false),
        Question("Чёрный дракон учил Ван Ван в лесу.",false),
        Question("Ван Ван использует арбалет Тана только во время ультимейта.",true),
        Question("Пассивка Ван Ван это Шаг тигра.",true)
    )

    val questionBankValir = listOf(
        Question("Валир пять лет жил на вулкане.",true),
        Question("Горд был тем, кто остановил битву Валира и Вейла.",true),
        Question("Вейл хотел убить Валира.",false),
        Question("Горд помог Вейлу в бою с Валиром.",false),
        Question("Тело Валира было всё в ожогах.",true),
        Question("Сражение с Валиром разбудило вулкан.",true)
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