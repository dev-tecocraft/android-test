package com.imaginato.homeworkmvvm.data.remote.login.request

import com.google.gson.annotations.SerializedName

data class LoginRequestModel(
    @SerializedName("username")
    val userName: String? = null,

    val password: String? = null,
)
