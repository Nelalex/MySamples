package com.nelalexxx.daggerhilt.domain

interface MyRepository {
    suspend fun doNetworkCall()
}