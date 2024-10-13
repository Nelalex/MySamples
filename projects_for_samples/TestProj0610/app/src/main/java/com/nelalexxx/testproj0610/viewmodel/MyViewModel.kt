package com.nelalexxx.testproj0610.viewmodel

import androidx.lifecycle.ViewModel

class MyViewModel(var name: String): ViewModel() {

    fun setText(text: String) : String {
        this.name = text + " " + name
        return name
    }


}