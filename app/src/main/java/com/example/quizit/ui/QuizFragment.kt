package com.example.quizit.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizit.QuizViewModel
import com.example.quizit.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    private lateinit var timer: CountDownTimer
    private lateinit var binding: FragmentQuizBinding
    private val viewModel: QuizViewModel by activityViewModels()

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
        val timerLength: Long = 20000

        viewModel.endGame.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(QuizFragmentDirections.actionQuizFragmentToEndResultFragment())
            }
        }

        viewModel.currentQuestion.observe(viewLifecycleOwner) {
            binding.quizQuestionTv.text = it.question
        }

        viewModel.startTimer(timerLength)

        viewModel.timeLeft.observe(viewLifecycleOwner) {
            binding.quizTimerTv.text = it.toString()
            if (it == 0L){
                viewModel.timerDelayToCheckAnswer(requireContext())
            }
        }

        binding.quizTrueBtn.setOnClickListener {
            viewModel.checkAnswer(true,requireContext())
            viewModel.cancelTimer()
            viewModel.startTimer(timerLength)
        }

        binding.quizFalseBtn.setOnClickListener {
            viewModel.checkAnswer(false,requireContext())
            viewModel.cancelTimer()
            viewModel.startTimer(timerLength)
        }
    }
}