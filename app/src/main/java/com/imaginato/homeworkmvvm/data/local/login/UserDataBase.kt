package com.imaginato.homeworkmvvm.data.local.login

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserData::class], version = 1, exportSchema = false)
abstract class UserDataBase: RoomDatabase() {
    abstract val userDao: UserDao
}