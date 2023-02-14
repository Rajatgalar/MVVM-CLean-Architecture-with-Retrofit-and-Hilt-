package com.itechnowizard.login_mvvm.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.itechnowizard.login_mvvm.R
import com.itechnowizard.login_mvvm.presentation.auth.LoginActivity
import com.itechnowizard.login_mvvm.presentation.productlist.ProductList
import com.itechnowizard.login_mvvm.utils.PrefUtils
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class SplashActivity : AppCompatActivity() {

    private var isLogin: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            checkLogin()
        }, 1000)
    }


    private fun checkLogin() {
        lifecycleScope.launch {
            isLogin = PrefUtils(this@SplashActivity).checkLogin.first()
            if (!isLogin) {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val mainIntent = Intent(this@SplashActivity, ProductList::class.java)
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(mainIntent)
                finish()
            }
        }

    }
}