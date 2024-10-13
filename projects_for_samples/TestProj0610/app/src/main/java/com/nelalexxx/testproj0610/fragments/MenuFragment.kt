package com.nelalexxx.testproj0610.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.nelalexxx.testproj0610.databinding.MenuFragmentBinding

class MenuFragment : BindingFragment<MenuFragmentBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = MenuFragmentBinding::inflate

   // private val fragmentViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.menuNextBtn.setOnClickListener {


            // без navcontroller
//            val bundle = Bundle()
//            bundle.putString("playerName", "Игрок 1") // Пример значения
//            findNavController().navigate(R.id.action_menuFragment_to_gameFragment, bundle)
            val action = MenuFragmentDirections.actionMenuFragmentToGameFragment(binding.checkBox.isChecked)
                findNavController().navigate(action)
        }
    }

}


