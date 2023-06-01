package com.example.warrenlogin.feature_user.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Transaction
    suspend fun saveUsers(users: List<UserDb>) {
        deleteAll()
        insertAll(users)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<UserDb>)

    @Query("SELECT * FROM users")
    fun getAll(): Flow<List<UserDb>>

    @Query("DELETE FROM users")
    suspend fun deleteAll()


}