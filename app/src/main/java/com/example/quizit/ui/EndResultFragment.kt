package com.example.quizit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizit.QuizViewModel
import com.example.quizit.R
import com.example.quizit.databinding.FragmentEndresultBinding

class EndResultFragment : Fragment() {

    private lateinit var binding: FragmentEndresultBinding
    private val viewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEndresultBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if (viewModel.score.value!! > 3) {
            binding.endresultBgIv.setImageResource(R.drawable.goodend)
        } else {
            binding.endresultBgIv.setImageResource(R.drawable.badend)
        }
        binding.endresultTv.text = "Your score: ${viewModel.score.value}/${viewModel.totalScore.value}"

        binding.endresultRestartBtn.setOnClickListener {
            viewModel.restartGame()
            findNavController().navigateUp()
        }
    }
}