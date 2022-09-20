package com.project.android.legend.Model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.android.legend.DataClass.Hero
import com.project.android.legend.DataClass.Question

class QuizViewModel : ViewModel(){

    val liveHero = MutableLiveData<List<Hero>>()
    private val heroQuestions = HeroQuestions()
    private val listHeroes = arrayListOf(
        Hero("Лейла", heroQuestions.questionsLayla, "0"),
        Hero("Заск", heroQuestions.questionsZask, "1"),
        Hero("Ван Ван", heroQuestions.questionsVanVan, "2"),
        Hero("Валир", heroQuestions.questionsValir, "3"),
        Hero("Беатрис", heroQuestions.questionsBeatris, "4"),
        Hero("Терзила", heroQuestions.questionsBeatris, "5"),
        Hero("Руби", heroQuestions.questionsBeatris, "6"),
        Hero("Тигрил", heroQuestions.questionsBeatris, "7"),
        Hero("Вейл", heroQuestions.questionsBeatris, "8"),
        Hero("Лилия", heroQuestions.questionsBeatris, "9"),
        Hero("Москов", heroQuestions.questionsBeatris, "10"),
        Hero("Мия", heroQuestions.questionsBeatris, "10"),
        Hero("Горд", heroQuestions.questionsBeatris, "11"),
        Hero("Клауд", heroQuestions.questionsBeatris, "12"),
        Hero("Тамуз", heroQuestions.questionsBeatris, "13"),
        Hero("Роджер", heroQuestions.questionsBeatris, "14"),
        Hero("Наташа", heroQuestions.questionsBeatris, "15"),
        Hero("Карина", heroQuestions.questionsBeatris, "16"),
        Hero("Госсен", heroQuestions.questionsBeatris, "17"),
        Hero("Бальмонд", heroQuestions.questionsBeatris, "18"),
        Hero("Атлас", heroQuestions.questionsBeatris, "19"),
        Hero("Франко", heroQuestions.questionsBeatris, "20"),
        Hero("Эймон", heroQuestions.questionsBeatris, "21"),

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
    private class HeroQuestions(

        val questionsLayla: List<Question> = listOf(
            Question("Противниками Лейлы стали ученые из лабы 1718.", true),
            Question("Отец Лейлы один из членов ученых лабы 1718.", true),
            Question("Губительную пушку Лейле подарил её дед.", false),
            Question("Лейлу схватили учёные из лабы 1718, потому что она меткий стрелок.", false),
            Question("В похищении Лейлы виновата Губительная Пушка.", true),
            Question("Истинные причины поступка отца, Лейле рассказал дед.", true)
        ),

        val questionsZask: List<Question> = listOf(
            Question("Заск король Роя Кастии.", true),
            Question("Заклятый враг Роя Кастии - Митлора.", true),
            Question("Охотник из Митлоры уничтожил Рой Кастии.", false),
            Question("Кастия уцелела после нападения охотника из Милторы.", false),
            Question("Своё вторжения в Земли Рассвета, Заск начал с северной долины.", true),
            Question("Именно Авроре удалось сильно ранить Заска.", true)
        ),

        val questionsVanVan: List<Question> = listOf(
            Question("Иглы Ван Ван это наследие Людей Тана.", true),
            Question("Судьбу Ван Ван навсегда изменил Чёрный Дракон.", true),
            Question("Чёрный дракон долго и упорно обучал Ван Ван.", false),
            Question("Чёрный дракон учил Ван Ван в лесу.", false),
            Question("Ван Ван использует арбалет Тана только во время ультимейта.", true),
            Question("Пассивка Ван Ван это Шаг тигра.", true)
        ),

        val questionsValir: List<Question> = listOf(
            Question("Валир пять лет жил на вулкане.", true),
            Question("Горд был тем, кто остановил битву Валира и Вейла.", true),
            Question("Вейл хотел убить Валира.", false),
            Question("Горд помог Вейлу в бою с Валиром.", false),
            Question("Тело Валира было всё в ожогах.", true),
            Question("Сражение с Валиром разбудило вулкан.", true)
        ),

        val questionsBeatris: List<Question> = listOf(
            Question("Многие поколения семьи Беатрис были богатыми торговцами.", true),
            Question("Беатрис поражала своими способностям к науке в университете.", true),
            Question("Проект Беатрис называла Нексус:Выжить.", false),
            Question("Ученым из лабы 1718 не удалось взломать проект.", false),
            Question("Беатрис забрала контроль на серверами у сумасшедшего биохимика Октавиуса.", true),
            Question("Беатрис подарила свою терминальную систему «Выживание: Нексус» Союзу ученых.", true)
        )
    )
}