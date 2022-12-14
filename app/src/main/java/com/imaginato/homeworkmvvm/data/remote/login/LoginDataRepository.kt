package com.imaginato.homeworkmvvm.data.remote.login

import com.imaginato.homeworkmvvm.data.remote.login.request.LoginRequestModel
import com.imaginato.homeworkmvvm.data.remote.login.response.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class LoginDataRepository(
    private var loginApi: LoginApi
) : LoginRepository {

    companion object {
        private const val URL_POST_LOGIN = "http://private-222d3-homework5.apiary-mock.com/api/login"
    }

    override suspend fun loginUser(loginRequestModel: LoginRequestModel): Flow<Response<LoginResponse>> = flow {
        val response = loginApi.loginUserAsync(URL_POST_LOGIN,loginRequestModel).await()
        emit(response)
    }.flowOn(Dispatchers.IO)
}