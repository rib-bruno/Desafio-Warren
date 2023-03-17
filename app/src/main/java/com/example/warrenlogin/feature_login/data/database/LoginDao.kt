package com.example.warrenlogin.feature_login.data.database

import androidx.room.*


@Dao
interface LoginDao {

    @Transaction
    suspend fun saveLogin(access: LoginDb) {
        deleteAll()
        insert(access)
    }

    @Query("SELECT * FROM access LIMIT 1")
    suspend fun getLogin(): LoginDb

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(login: LoginDb)

    @Query("DELETE FROM access" )
    suspend fun deleteAll()
}