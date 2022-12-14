package com.imaginato.homeworkmvvm.ui.login

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.component.KoinApiExtension


@KoinApiExtension
@RunWith(AndroidJUnit4::class)
class LoginActivityViewModelTest : TestCase(){
    private lateinit var viewModel: LoginActivityViewModel

    @Before
    public override fun setUp() {
        super.setUp()
        viewModel = LoginActivityViewModel()
    }

    @Test
    fun testLoginViewModelForValidEmail(){
        viewModel.userEnteredEmail = "test@test.ts"
        val isValidEmail = viewModel.isValidEmail()
        assertTrue(isValidEmail)
    }

    @Test
    fun testLoginViewModelForInValidEmail(){
        viewModel.userEnteredEmail = "test @test.ts "
        val isInValidEmail = viewModel.isValidEmail()
        assertFalse(isInValidEmail)
    }

}