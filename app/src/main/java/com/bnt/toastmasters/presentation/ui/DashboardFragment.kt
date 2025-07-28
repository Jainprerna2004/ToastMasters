package com.bnt.toastmasters.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.cardview.widget.CardView
import android.widget.TextView
import android.widget.Toast
import com.bnt.toastmasters.R

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Set up dashboard content
        setupDashboardContent(view)
    }
    
    private fun setupDashboardContent(view: View) {
        // Welcome message
        val welcomeText = view.findViewById<TextView>(R.id.welcome_text)
        welcomeText.text = "Welcome to ToastMasters!"
        
        // Quick stats
        val upcomingMeetingsCard = view.findViewById<CardView>(R.id.upcoming_meetings_card)
        upcomingMeetingsCard.setOnClickListener {
            Toast.makeText(context, "Upcoming Meetings", Toast.LENGTH_SHORT).show()
        }
        
        val myProgressCard = view.findViewById<CardView>(R.id.my_progress_card)
        myProgressCard.setOnClickListener {
            Toast.makeText(context, "My Progress", Toast.LENGTH_SHORT).show()
        }
        
        val recentActivitiesCard = view.findViewById<CardView>(R.id.recent_activities_card)
        recentActivitiesCard.setOnClickListener {
            Toast.makeText(context, "Recent Activities", Toast.LENGTH_SHORT).show()
        }
        
        val quickActionsCard = view.findViewById<CardView>(R.id.quick_actions_card)
        quickActionsCard.setOnClickListener {
            Toast.makeText(context, "Quick Actions", Toast.LENGTH_SHORT).show()
        }
    }
} 