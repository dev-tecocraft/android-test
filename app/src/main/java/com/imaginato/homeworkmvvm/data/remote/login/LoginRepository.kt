package com.imaginato.homeworkmvvm.data.remote.login

import com.imaginato.homeworkmvvm.data.remote.login.request.LoginRequestModel
import com.imaginato.homeworkmvvm.data.remote.login.response.LoginResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface LoginRepository {
    suspend fun loginUser(loginRequestModel: LoginRequestModel): Flow<Response<LoginResponse>>
}