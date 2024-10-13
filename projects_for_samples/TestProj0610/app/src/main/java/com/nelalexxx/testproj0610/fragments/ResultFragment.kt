package com.nelalexxx.testproj0610.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.nelalexxx.testproj0610.databinding.ResultFragmentBinding

class ResultFragment : BindingFragment<ResultFragmentBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = ResultFragmentBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}