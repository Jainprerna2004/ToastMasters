package com.bnt.toastmasters

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.bnt.toastmasters.auth.presentation.AuthActivity
import com.bnt.toastmasters.data.repository.UserRepositoryImpl
import com.bnt.toastmasters.domain.User
import com.bnt.toastmasters.domain.UserRole
import com.bnt.toastmasters.presentation.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import com.bnt.toastmasters.presentation.ui.BackoutMembersFragment
import com.bnt.toastmasters.presentation.ui.CreateFragment
import com.bnt.toastmasters.presentation.ui.DashboardFragment
import com.bnt.toastmasters.presentation.ui.LeaderboardFragment
import com.bnt.toastmasters.presentation.ui.ReportsFragment
import com.bnt.toastmasters.presentation.ui.SettingsFragment
import com.bnt.toastmasters.presentation.ui.UpcomingMeetingsFragment
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var viewModel: MainViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Initialize ViewModel with repository
        val userRepository = UserRepositoryImpl()
        viewModel = MainViewModel(userRepository)
        
        // TODO: Replace with real session check
        val userLoggedIn = true // Assume user is logged in if coming from login
        if (!userLoggedIn) {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        } else {
            enableEdgeToEdge()
            setContentView(R.layout.activity_main)
            
            bottomNavigation = findViewById(R.id.bottom_navigation)
            setupRoleBasedNavigation()
            
            // Set default fragment
            if (savedInstanceState == null) {
                supportFragmentManager.commit {
                    replace(R.id.fragment_container, DashboardFragment())
                }
            }
            
            // For demo purposes, set a VP Education user
            // Change UserRole.VP_EDUCATION to UserRole.MEMBER to test member view
            lifecycleScope.launch {
                val demoUser = User(
                    id = "1",
                    name = "John Doe",
                    email = "john@toastmasters.com",
                    role = UserRole.VP_EDUCATION // Change to UserRole.MEMBER to test member view
                )
                viewModel.setCurrentUser(demoUser)
            }
            
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
    
    private fun setupRoleBasedNavigation() {
        // Observe navigation items from ViewModel
        lifecycleScope.launch {
            viewModel.navigationItems.collect { navigationItems ->
                updateBottomNavigation(navigationItems)
            }
        }
        
        bottomNavigation.setOnItemSelectedListener { menuItem ->
            val fragment: Fragment = when (menuItem.itemId) {
                R.id.navigation_dashboard -> DashboardFragment()
                R.id.navigation_leaderboard -> LeaderboardFragment()
                R.id.navigation_plus -> CreateFragment()
                R.id.navigation_reports -> ReportsFragment()
                R.id.navigation_settings -> SettingsFragment()
                R.id.navigation_backout_members -> BackoutMembersFragment()
                R.id.navigation_upcoming_meetings -> UpcomingMeetingsFragment()
                else -> DashboardFragment()
            }
            
            supportFragmentManager.commit {
                replace(R.id.fragment_container, fragment)
            }
            true
        }
    }
    
    private fun updateBottomNavigation(navigationItems: List<MainViewModel.NavigationItem>) {
        // Clear existing menu
        bottomNavigation.menu.clear()
        
        // Add navigation items based on user role
        navigationItems.forEach { item ->
            bottomNavigation.menu.add(0, item.id, 0, item.title).apply {
                setIcon(item.icon)
            }
        }
        
        // Set the first item as selected
        if (navigationItems.isNotEmpty()) {
            bottomNavigation.selectedItemId = navigationItems.first().id
        }
    }
}