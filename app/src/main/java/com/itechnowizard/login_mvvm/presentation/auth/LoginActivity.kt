package com.itechnowizard.login_mvvm.presentation.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.itechnowizard.login_mvvm.databinding.ActivityLoginBinding
import com.itechnowizard.login_mvvm.presentation.productlist.ProductList
import com.itechnowizard.login_mvvm.utils.PrefUtils
import kotlinx.coroutines.runBlocking

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
            btnLogin.setOnClickListener {
                if(checkValidation()){
                    runBlocking {
                        PrefUtils(this@LoginActivity).saveLogin(true)
                    }
                    val mainIntent = Intent(this@LoginActivity,ProductList::class.java)
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(mainIntent)
                    finish()
                }else{
                    Toast.makeText(this@LoginActivity,"Incomplete details",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkValidation(): Boolean {
        if(binding.etUsername.text.isNullOrEmpty())
            return false
        else if(binding.etPassword.text.isNullOrEmpty())
            return false
        return true
    }
}