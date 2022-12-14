package com.imaginato.homeworkmvvm.data.local.login

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userData: UserData)

    @Query("SELECT * FROM user")
    fun getUserData() : LiveData<List<UserData>>
}