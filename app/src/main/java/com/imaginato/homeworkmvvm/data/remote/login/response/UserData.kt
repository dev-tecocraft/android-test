package com.imaginato.homeworkmvvm.data.remote.login.response

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("userId") var userId: String? = null,
    @SerializedName("userName") var userName: String? = null,
    @SerializedName("isDeleted") var isDeleted: Boolean? = null
) {
    fun toUserData(xAccHeader: String?): com.imaginato.homeworkmvvm.data.local.login.UserData {
        return com.imaginato.homeworkmvvm.data.local.login.UserData(
            userId = userId,
            userName = userName,
            xAccToken = xAccHeader
        )
    }
}