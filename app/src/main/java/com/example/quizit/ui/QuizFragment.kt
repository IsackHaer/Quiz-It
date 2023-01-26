package com.example.quizit.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.quizit.QuizViewModel
import com.example.quizit.databinding.FragmentQuizBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class QuizFragment : Fragment() {

    private lateinit var timer: CountDownTimer
    private lateinit var binding: FragmentQuizBinding
    private val viewModel: QuizViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //first initialization
        updateUI()
        startTimer()

        binding.quizTrueBtn.setOnClickListener {
            viewModel.checkAnswer(true)
            timer.cancel()
            updateUI()
            startTimer()
        }

        binding.quizFalseBtn.setOnClickListener {
            viewModel.checkAnswer(false)
            timer.cancel()
            updateUI()
            startTimer()
        }
    }

    private fun startTimer(){
        timer = object : CountDownTimer(15000,1000){
            override fun onTick(millisUntilFinished: Long) {
                var timeLeft = millisUntilFinished / 1000
                binding.quizTimerTv.text = timeLeft.toString()
            }

            override fun onFinish() {
                startTimer()
            }

        }
        if (viewModel.totalScore < 5)
            timer.start()
    }

    private fun updateUI(){
        binding.quizScoreTv.text = viewModel.score.toString()
        binding.quizQuestionTv.text = viewModel.currentQuestion.question

        if (viewModel.totalScore == 5) {
            showEndDialog()
        }
    }

    private fun showEndDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Good job!")
            .setMessage("You got ${viewModel.score}/${viewModel.totalScore} questions right!")
            .setCancelable(false)
            .setNegativeButton("Quit") { _,_ ->
                activity?.finish()
            }
            .setPositiveButton("Restart") { _,_ ->
                viewModel.restartGame()
                updateUI()
            }
            .show()
    }
}