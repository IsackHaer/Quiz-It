package com.example.quizit

import androidx.lifecycle.ViewModel
import com.example.quizit.data.QuizRepository
import com.example.quizit.data.model.Question

class QuizViewModel : ViewModel() {

    private val repository = QuizRepository()
    private val questions = repository.loadQuestions()

    private var _totalScore = 0
    val totalScore: Int
        get() = _totalScore

    private var _score = 0
    val score: Int
        get() = _score

    private var _currentQuestion = questions.random()
    val currentQuestion: Question
        get() = _currentQuestion

    fun checkAnswer(isTrue: Boolean?){
        if (isTrue == _currentQuestion.answer){
            _score++
            _totalScore++
            getNextQuestion()
        } else {
            _totalScore++
            getNextQuestion()
        }
    }

    fun getNextQuestion() {
        val nextQuestion = questions.random()

        if (_currentQuestion == nextQuestion){
            getNextQuestion()
        } else {
            _currentQuestion = nextQuestion
        }
    }

    fun restartGame(){
        _score = 0
        _totalScore = 0
        getNextQuestion()
    }
}