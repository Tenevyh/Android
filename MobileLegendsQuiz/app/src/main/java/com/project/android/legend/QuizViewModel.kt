package com.project.android.legend

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel(){

    val liveHero = MutableLiveData<List<Hero>>()
    private val heroQuestions = HeroQuestions()
    private val listHeroes = arrayListOf(
        Hero("Лейла", heroQuestions.questionsLayla, "0"),
        Hero("Заск", heroQuestions.questionsZask, "1"),
        Hero("Ван Ван", heroQuestions.questionsVanVan, "2"),
        Hero("Валир", heroQuestions.questionsValir, "3"),
        Hero("Беатрис", heroQuestions.questionsBeatris, "4")
        )


    private var questionBank = listOf(
        Question("Для начала викторины необходимо выбрать героя",true),
        Question("", true)
    )

    fun getHero(): ArrayList<Hero> {
        return listHeroes
    }

    var currentIndex = 0
    var questionIndex = -1
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

    fun clearResult(){
         currentIndex = 0
         questionIndex = -1
         correctIndex = 0
         inCorrectIndex = 0
         cheatIndex = 0
         showAnswer = false
        for (question in questionBank){
            question.completed=false
        }
    }
}