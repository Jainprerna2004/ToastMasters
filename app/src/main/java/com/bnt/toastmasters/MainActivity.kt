package com.bnt.toastmasters

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bnt.toastmasters.auth.presentation.AuthActivity
import androidx.fragment.app.commit
import com.bnt.toastmasters.VpDashboardFragment
import com.bnt.toastmasters.MemberDashboardFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: Replace with real session check
        val userLoggedIn = true // Assume user is logged in if coming from login
        if (!userLoggedIn) {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        } else {
            enableEdgeToEdge()
            setContentView(R.layout.activity_main)
            val userType = intent.getStringExtra("userType") ?: "member"
            val fragment = if (userType == "vp") VpDashboardFragment() else MemberDashboardFragment()
            supportFragmentManager.commit {
                replace(R.id.fragment_container, fragment)
            }
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
}