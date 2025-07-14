package com.bnt.toastmasters.auth.presentation

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.bnt.toastmasters.R

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.auth_fragment_container, LoginFragment())
            }
        }
    }
    fun showSignup() {
        supportFragmentManager.commit {
            replace(R.id.auth_fragment_container, SignupFragment())
            addToBackStack(null)
        }
    }
    fun showLogin() {
        supportFragmentManager.commit {
            replace(R.id.auth_fragment_container, LoginFragment())
        }
    }
} 