package com.nelalexxx.daggerhilt.di

import com.nelalexxx.daggerhilt.domain.MyRepository
import com.nelalexxx.daggerhilt.domain.repository.RepositoryImpl
import com.nelalexxx.daggerhilt.remote.MyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // lifecycle of your singleton object, you can ActivityComponent ViewModelComponent ActivityRetainedComponent and the others
object AppModule {

    @Provides // provides a dependency
    @Singleton // its a scope that means that it wont create new instance for each object that using this functionality
    fun provideMyApi() : MyApi {
        return Retrofit.Builder()
            .baseUrl("https//test.com")
            .build()
            .create(MyApi::class.java)
    }


    @Provides
    @Singleton
    fun provideMyRepository(api: MyApi): MyRepository {
        return RepositoryImpl(api = api)
    }

}