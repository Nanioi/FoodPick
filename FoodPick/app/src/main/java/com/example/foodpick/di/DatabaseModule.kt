package com.example.foodpick.di

import android.content.Context
import androidx.room.Room
import com.example.foodpick.data.local.AppDatabase
import com.example.foodpick.data.local.dao.StoreDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app-db").build()

    @Provides
    fun provideStoreDao(db: AppDatabase): StoreDao = db.storeDao()

}
