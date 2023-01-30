package com.example.quizit

import android.content.Context
import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizit.data.QuizRepository
import com.example.quizit.data.model.Question
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {

    private val repository = QuizRepository()
    private val questions = repository.loadQuestions().toMutableList()
    private val previousQuestions = mutableListOf<Question>()

    private var _timeLeft = MutableLiveData<Long>()
    val timeLeft: LiveData<Long>
        get() = _timeLeft
    private var timer: CountDownTimer? = null
    private var isRunning = false

    private var _totalScore = MutableLiveData<Int>(0)
    val totalScore: LiveData<Int>
        get() = _totalScore

    private var _score = MutableLiveData<Int>(0)
    val score: LiveData<Int>
        get() = _score

    private var _currentQuestion = MutableLiveData<Question>(questions.random())
    val currentQuestion: LiveData<Question>
        get() = _currentQuestion

    private var _endGame = MutableLiveData<Boolean>(false)
    val endGame: LiveData<Boolean>
        get() = _endGame

    fun checkAnswer(isTrue: Boolean?, context: Context){
        if (_totalScore.value == 4) {
            _endGame.value = true
        }
        if (isTrue == _currentQuestion.value?.answer){
                _score.value = _score.value?.plus(1)
                _totalScore.value = _totalScore.value?.plus(1)
            Toast.makeText(context, "Correct", Toast.LENGTH_SHORT).show()
            getNextQuestion()
        } else {
            _totalScore.value = _totalScore.value?.plus(1)
            getNextQuestion()
            Toast.makeText(context, "Wrong...", Toast.LENGTH_SHORT).show()
        }
    }

    fun getNextQuestion() {
        if (questions.isEmpty() ){
            questions.addAll(previousQuestions)
            previousQuestions.clear()
        }

        val nextQuestion = questions.random()

        previousQuestions.add(nextQuestion)
        questions.remove(nextQuestion)

        _currentQuestion.value = nextQuestion
    }

    fun restartGame(){
        _score.value = 0
        _totalScore.value = 0
        _endGame.value = false
        cancelTimer()
        getNextQuestion()
    }

    fun startTimer(time: Long){
        if (isRunning) return
        timer = object : CountDownTimer(time,1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timeLeft.value = millisUntilFinished / 1000
                isRunning = true
            }

            override fun onFinish() {
                cancelTimer()
                startTimer(time)
            }
        }.start()
    }

    fun cancelTimer(){
        timer?.cancel()
        isRunning = false
    }

    //onCleared method of the ViewModel, the timer is canceled, to prevent any memory leaks.
    override fun onCleared(){
        super.onCleared()
        timer?.cancel()
    }

    // this is to allow next question to load after timer hits 0
    fun timerDelayToCheckAnswer(context: Context){
        viewModelScope.launch {
            delay(1000)
            checkAnswer(null, context)
        }
    }
}