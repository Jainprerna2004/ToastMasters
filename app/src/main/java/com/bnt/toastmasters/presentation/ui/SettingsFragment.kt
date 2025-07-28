package com.bnt.toastmasters.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.cardview.widget.CardView
import android.widget.Toast
import com.bnt.toastmasters.R

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Set up click listeners for the settings options
        view.findViewById<CardView>(R.id.profile_settings_card).setOnClickListener {
            Toast.makeText(context, "Profile Settings", Toast.LENGTH_SHORT).show()
        }
        
        view.findViewById<CardView>(R.id.notification_settings_card).setOnClickListener {
            Toast.makeText(context, "Notification Settings", Toast.LENGTH_SHORT).show()
        }
        
        view.findViewById<CardView>(R.id.privacy_settings_card).setOnClickListener {
            Toast.makeText(context, "Privacy Settings", Toast.LENGTH_SHORT).show()
        }
        
        view.findViewById<CardView>(R.id.about_card).setOnClickListener {
            Toast.makeText(context, "About ToastMasters", Toast.LENGTH_SHORT).show()
        }
        
        view.findViewById<CardView>(R.id.logout_card).setOnClickListener {
            Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()
        }
    }
} 