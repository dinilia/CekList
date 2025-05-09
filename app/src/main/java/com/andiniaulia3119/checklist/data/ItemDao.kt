package com.andiniaulia3119.checklist.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemDao {
    @Insert
    suspend fun insertItem(item: Item)

    @Query("SELECT * FROM items")
    suspend fun getAllItems(): List<Item>

    @Delete
    suspend fun delete(item: Item)

    @Update
    suspend fun update(item: Item)
}
