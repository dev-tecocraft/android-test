package com.imaginato.homeworkmvvm.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.imaginato.homeworkmvvm.databinding.ActivityLoginBinding
import com.imaginato.homeworkmvvm.exts.getTextAfterChanged
import com.imaginato.homeworkmvvm.ui.user.UserActivity
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModel<LoginActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initStateObserver()
        initViews()
        checkUserLoggedIn()
    }

    private fun checkUserLoggedIn() {
        viewModel.getUserData().observe(this@LoginActivity){ userDataList ->
            userDataList?.let {
                if(userDataList.isNotEmpty()){
                    startActivity(
                        Intent(this@LoginActivity, UserActivity::class.java).apply {
                            putExtra("userData", userDataList[0])
                        }
                    )
                    finish()
                }
            }
        }
    }

    private fun initStateObserver() {
        lifecycleScope.launchWhenStarted {
            viewModel.loginUiState.collect { uiState ->
                when (uiState) {
                    LoginActivityViewModel.LoginUiState.Empty -> {
                        onLoadingContent(false)
                    }
                    LoginActivityViewModel.LoginUiState.InValidEmail -> {
                        onInValidEmailEntered(true)
                    }
                    LoginActivityViewModel.LoginUiState.Loading -> {
                        onInValidEmailEntered(false)
                        onLoadingContent(true)
                    }
                    is LoginActivityViewModel.LoginUiState.OnSuccess -> {
                        onLoadingContent(false)
                    }
                    is LoginActivityViewModel.LoginUiState.OnError -> {
                        onLoadingContent(false)
                        Toast.makeText(this@LoginActivity, uiState.errorMessage, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun onLoadingContent(isLoading: Boolean) {
        binding.apply {
            btnLogin.isEnabled = !isLoading
            cvLoading.isVisible = isLoading
        }
        checkForLoginButtonEnable()
    }

    private fun onInValidEmailEntered(isInValid: Boolean) {
        binding.tvInvalidEmail.isVisible = isInValid
    }

    private fun initViews() {
        binding.apply {
            etEmail.getTextAfterChanged { text ->
                viewModel.userEnteredEmail = text
                checkForLoginButtonEnable()
            }
            etPassword.getTextAfterChanged { password ->
                viewModel.userEnteredPassword = password
                checkForLoginButtonEnable()
            }
            btnLogin.setOnClickListener {
                viewModel.loginUser()
            }
            ivClose.setOnClickListener { finish() }
        }
    }

    private fun checkForLoginButtonEnable() {
        binding.btnLogin.isEnabled =
            viewModel.userEnteredEmail.isNotEmpty() && viewModel.userEnteredPassword.isNotEmpty()
    }
}