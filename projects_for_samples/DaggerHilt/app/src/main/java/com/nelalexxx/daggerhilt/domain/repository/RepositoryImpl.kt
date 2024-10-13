package com.nelalexxx.daggerhilt.domain.repository

import com.nelalexxx.daggerhilt.domain.MyRepository
import com.nelalexxx.daggerhilt.remote.MyApi

class RepositoryImpl(
    private val api: MyApi
): MyRepository{
    override suspend fun doNetworkCall() {

    }
}