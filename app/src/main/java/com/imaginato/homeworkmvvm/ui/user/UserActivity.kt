package com.imaginato.homeworkmvvm.ui.user

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.imaginato.homeworkmvvm.R
import com.imaginato.homeworkmvvm.data.local.login.UserData
import com.imaginato.homeworkmvvm.databinding.ActivityUserBinding
import com.imaginato.homeworkmvvm.ui.login.LoginActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class UserActivity : AppCompatActivity() {

    var userData: UserData? = null
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intiArguments()
        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        userData?.let {
            binding.apply {
                tvUserName.text = "UserName : ${it.userName}"
                tvUserId.text = "UserId : ${it.userId}"
            }
        }
    }

    private fun intiArguments() {
        intent.extras?.let { bundle ->
            userData = bundle.getSerializable("userData") as UserData
        }
    }
}