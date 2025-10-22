package com.example.foodpick.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodpick.data.local.dao.StoreDao
import com.example.foodpick.data.local.entity.StoreEntity

@Database(entities = [StoreEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun storeDao(): StoreDao
}
