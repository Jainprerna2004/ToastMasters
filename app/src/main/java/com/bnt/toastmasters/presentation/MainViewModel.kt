package com.bnt.toastmasters.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bnt.toastmasters.R
import com.bnt.toastmasters.domain.User
import com.bnt.toastmasters.domain.UserRole
import com.bnt.toastmasters.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()
    
    private val _navigationItems = MutableStateFlow<List<NavigationItem>>(emptyList())
    val navigationItems: StateFlow<List<NavigationItem>> = _navigationItems.asStateFlow()
    
    init {
        loadCurrentUser()
    }
    
    private fun loadCurrentUser() {
        viewModelScope.launch {
            val user = userRepository.getCurrentUser()
            _currentUser.value = user
            updateNavigationItems(user)
        }
    }
    
    private fun updateNavigationItems(user: User?) {
        val items = when (user?.role) {
            UserRole.VP_EDUCATION -> getVpEducationNavigationItems()
            UserRole.MEMBER -> getMemberNavigationItems()
            else -> getDefaultNavigationItems()
        }
        _navigationItems.value = items
    }
    
    private fun getVpEducationNavigationItems(): List<NavigationItem> {
        return listOf(
            NavigationItem.Dashboard,
            NavigationItem.Reports,
            NavigationItem.Settings,
            NavigationItem.CreateMeeting,
            NavigationItem.Leaderboard,
            NavigationItem.BackoutMembers
        )
    }
    
    private fun getMemberNavigationItems(): List<NavigationItem> {
        return listOf(
            NavigationItem.Dashboard,
            NavigationItem.Settings,
            NavigationItem.UpcomingMeetings,
            NavigationItem.Leaderboard
        )
    }
    
    private fun getDefaultNavigationItems(): List<NavigationItem> {
        return listOf(NavigationItem.Dashboard)
    }
    
    fun setCurrentUser(user: User) {
        viewModelScope.launch {
            userRepository.saveUser(user)
            _currentUser.value = user
            updateNavigationItems(user)
        }
    }
    
    fun logout() {
        viewModelScope.launch {
            userRepository.clearUser()
            _currentUser.value = null
            _navigationItems.value = getDefaultNavigationItems()
        }
    }
}

sealed class NavigationItem(val id: Int, val title: String, val icon: Int) {
    object Dashboard : NavigationItem(R.id.navigation_dashboard, "Dashboard", R.drawable.ic_dashboard)
    object Reports : NavigationItem(R.id.navigation_reports, "Reports", R.drawable.ic_reports)
    object Settings : NavigationItem(R.id.navigation_settings, "Settings", R.drawable.ic_settings)
    object CreateMeeting : NavigationItem(R.id.navigation_plus, "Create Meeting", R.drawable.ic_add)
    object Leaderboard : NavigationItem(R.id.navigation_leaderboard, "Leaderboard", R.drawable.ic_leaderboard)
    object BackoutMembers : NavigationItem(R.id.navigation_backout_members, "Backout Members", R.drawable.ic_backout)
    object UpcomingMeetings : NavigationItem(R.id.navigation_upcoming_meetings, "Upcoming Meetings", R.drawable.ic_meeting)
} 