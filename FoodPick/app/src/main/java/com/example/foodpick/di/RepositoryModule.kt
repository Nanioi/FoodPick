package com.example.foodpick.di

import com.example.foodpick.data.local.datasource.LocalDataSourceImpl
import com.example.foodpick.data.remote.datasource.RemoteDataSourceImpl
import com.example.foodpick.data.repository.StoreRepositoryImpl
import com.example.foodpick.domain.repository.StoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideStoreRepository(remote: RemoteDataSourceImpl, local: LocalDataSourceImpl ): StoreRepository = StoreRepositoryImpl(remote, local)
}
