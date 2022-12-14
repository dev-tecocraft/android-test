package com.imaginato.homeworkmvvm.ui.login

import androidx.lifecycle.viewModelScope
import com.imaginato.homeworkmvvm.data.local.login.UserDao
import com.imaginato.homeworkmvvm.data.remote.login.LoginRepository
import com.imaginato.homeworkmvvm.data.remote.login.request.LoginRequestModel
import com.imaginato.homeworkmvvm.data.remote.login.response.UserData
import com.imaginato.homeworkmvvm.ui.base.BaseViewModel
import com.imaginato.homeworkmvvm.utils.ApiConstants.ERROR_CODE
import com.imaginato.homeworkmvvm.utils.ApiConstants.ERROR_MESSAGE
import com.imaginato.homeworkmvvm.utils.ApiConstants.SUCCESS_CODE
import com.imaginato.homeworkmvvm.utils.ApiConstants.X_ACC_HEADER
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.Headers
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.inject

@KoinApiExtension
class LoginActivityViewModel : BaseViewModel() {

    private val loginRepository: LoginRepository by inject()
    private val userDao: UserDao by inject()

    private var _loginUIState = MutableStateFlow<LoginUiState>(LoginUiState.Empty)
    val loginUiState = _loginUIState.asStateFlow()

    var userEnteredEmail: String = ""
    var userEnteredPassword: String = ""

    fun loginUser() {
        if (!isValidEmail()) {
            _loginUIState.value = LoginUiState.InValidEmail
            return
        }
        _loginUIState.value = LoginUiState.Loading
        viewModelScope.launch {
            loginRepository.loginUser(
                LoginRequestModel(
                    userName = userEnteredEmail, password = userEnteredPassword
                )
            ).onStart {
                _loginUIState.value = LoginUiState.Loading
            }.catch {
                _loginUIState.value = LoginUiState.Empty
            }.onCompletion {}.collect { loginApiResponse ->
                if (loginApiResponse.isSuccessful) {
                    val loginResponse = loginApiResponse.body()
                    val headerList: Headers = loginApiResponse.headers()
                    val xAccHeader = headerList[X_ACC_HEADER]
                    if (loginResponse == null) {
                        _loginUIState.value = LoginUiState.OnError(ERROR_MESSAGE)
                    } else {
                        loginResponse.let {
                            it.errorCode?.let { errorCode ->
                                if (errorCode == SUCCESS_CODE) {
                                    loginResponse.data?.let { userData ->
                                        Thread {
                                            userDao.insertUser(userData.toUserData(xAccHeader))
                                        }.start()
                                    }
                                    _loginUIState.value =
                                        LoginUiState.OnSuccess(loginResponse.data, xAccHeader)
                                } else if (errorCode == ERROR_CODE && loginResponse.errorMessage != null) {
                                    _loginUIState.value =
                                        LoginUiState.OnError(loginResponse.errorMessage!!)
                                } else {
                                    _loginUIState.value =
                                        LoginUiState.OnError(ERROR_MESSAGE)
                                }
                            }

                        }
                    }
                } else {
                    _loginUIState.value = LoginUiState.OnError(ERROR_MESSAGE)
                }
            }
        }
    }

    fun isValidEmail() =
        android.util.Patterns.EMAIL_ADDRESS.matcher(userEnteredEmail).matches()

    fun getUserData() = userDao.getUserData()


    sealed class LoginUiState {
        object Empty : LoginUiState()
        object InValidEmail : LoginUiState()
        object Loading : LoginUiState()
        data class OnSuccess(val userData: UserData?, val xAccToken: String?) : LoginUiState()
        data class OnError(val errorMessage: String) : LoginUiState()
    }
}