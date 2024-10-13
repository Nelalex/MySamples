package com.nelalexxx.testproj0610.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.nelalexxx.testproj0610.R
import com.nelalexxx.testproj0610.databinding.GameFragmentBinding
import com.nelalexxx.testproj0610.viewmodel.MyViewModel
import com.nelalexxx.testproj0610.viewmodel.MyViewModelFactory

class GameFragment : BindingFragment<GameFragmentBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = GameFragmentBinding::inflate

    private val viewModel: MyViewModel by activityViewModels { MyViewModelFactory("Sanya")  }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.addBtn.setOnClickListener {
            binding.gameTV1.text = viewModel.setText(binding.gameTV1.text.toString())
        }


         if (GameFragmentArgs.fromBundle(requireArguments()).state)
             binding.gameTV2.text = "ПИДАРАС"

        binding.gameNextBtn.setOnClickListener {
            findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
        }
    }
}