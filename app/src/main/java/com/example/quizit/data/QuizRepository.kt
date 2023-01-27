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
            Question("During one of Paul’s lengthy sermons, Tychicus fell out of the window and died.", true),
            Question("In Jericho, Jesus noticed Zacchaeus climbing a sycamore tree.", true),
            Question("Joshua dispatched three spies to Jericho, who took refuge in Rahab’s house.", false),
            Question("On Mt. Sinai, the Ten Commandments were given to Aaron.", false),
            Question("Daniel, Shadrach, Meshach, and Abednego were burned alive in a fiery furnace.", false),
            Question("During Queen Esther’s reign, Haman plotted to kill the Jews.", true),
            Question("Brimstone and fire from heaven destroyed the Tower of Babel.", false),
            Question("Joseph’s brothers sold him into slavery.",true),
            Question("An angel stopped Balaam’s camel from passing.", true),
            Question("To be cured of his leprosy, Naaman was instructed to bathe seven times in the Jordan River.", true),
            Question("On the Sabbath, Jesus healed the man with the withered hand.", true),
            Question("The number of Wise Men who attended Jesus’ birth will remain a mystery for the rest of time.", false),
            Question("Luke, the apostle, was a tax collector.", false)
        ).shuffled()
    }
}