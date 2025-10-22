package com.example.foodpick.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodpick.data.local.entity.StoreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreDao {

    @Query("SELECT * FROM favorite_store")
    fun getFavoriteStores(): Flow<List<StoreEntity>>

    @Query("SELECT EXISTS(SELECT * FROM favorite_store WHERE id = :id)")
    suspend fun isLiked(id: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun likeStore(store: StoreEntity)

//    @Delete
//    suspend fun unlikeStore(store: StoreEntity)

    @Query("DELETE FROM favorite_store WHERE id = :id")
    suspend fun unlikeStoreById(id: String)
}
