package com.imaginato.homeworkmvvm.data.remote.login

import com.imaginato.homeworkmvvm.data.remote.login.request.LoginRequestModel
import com.imaginato.homeworkmvvm.data.remote.login.response.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface LoginApi {
    @POST
    fun loginUserAsync(
        @Url url: String,
        @Body loginRequestModel: LoginRequestModel
    ): Deferred<Response<LoginResponse>>
}