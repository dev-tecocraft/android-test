package com.imaginato.homeworkmvvm.data.local.login

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class UserData(
    @PrimaryKey
    val id: Long? = 0L,
    @ColumnInfo(name = "user_name")
    var userName: String?,
    @ColumnInfo(name = "user_id")
    var userId: String?,
    @ColumnInfo(name = "x_acc_token")
    var xAccToken: String?
) : java.io.Serializable
