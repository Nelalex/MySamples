package com.nelalexxx.daggerhilt

import com.nelalexxx.daggerhilt.domain.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: MyRepository
) {


}