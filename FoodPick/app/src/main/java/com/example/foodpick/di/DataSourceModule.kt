package com.example.foodpick.di

import com.example.foodpick.data.local.datasource.LocalDataSource
import com.example.foodpick.data.local.datasource.LocalDataSourceImpl
import com.example.foodpick.data.remote.datasource.RemoteDataSource
import com.example.foodpick.data.remote.datasource.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindStoreRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource
    @Binds
    abstract fun bindStoreLocalDataSource(impl: LocalDataSourceImpl): LocalDataSource
}