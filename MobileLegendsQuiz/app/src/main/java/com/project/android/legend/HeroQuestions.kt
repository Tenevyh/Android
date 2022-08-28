package com.project.android.legend

class HeroQuestions(

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