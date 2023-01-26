package com.example.quizit.data

import com.example.quizit.data.model.Question

class QuizRepository {

    fun loadQuestions() : List<Question>{
        return listOf<Question>(
            Question("Jesus was born in the town of Nazareth.", true),
            Question("Ham, Shem, and Japheth were Noah’s three sons.", true),
            Question("Moses fled to Midian after killing an Egyptian.", true),
            Question("At the wedding in Damascus, Jesus turned water into wine.", false),
            Question("God dispatched Jonah to Nineveh.", true),
            Question("Jesus healed Lazarus of his blindness.", false),
            Question("The tax collector passed by on the other side in the parable of the Good Samaritan.", false),
            Question("Isaac was Abraham’s first son.", false),
            Question("On the way to Damascus, Paul was converted.", true),
            Question("The 5,000 people were fed with five loaves and two fish.", true),
            Question("Moses led the children of Israel across the Jordan River into the Promised Land.", false),
            Question("The pure of heart will be blessed because they will see God.", true),
            Question("During one of Paul’s lengthy sermons, Tychicus fell out of the window and died.", true)
        ).shuffled()
    }
}