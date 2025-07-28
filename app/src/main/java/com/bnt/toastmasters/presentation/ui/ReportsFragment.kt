package com.bnt.toastmasters.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.cardview.widget.CardView
import android.widget.Toast
import com.bnt.toastmasters.R

class ReportsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reports, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Set up click listeners for the report options
        view.findViewById<CardView>(R.id.meeting_summary_report_card).setOnClickListener {
            Toast.makeText(context, "Meeting Summary Report", Toast.LENGTH_SHORT).show()
        }
        
        view.findViewById<CardView>(R.id.user_wise_report_card).setOnClickListener {
            Toast.makeText(context, "User Wise Report", Toast.LENGTH_SHORT).show()
        }
        
        view.findViewById<CardView>(R.id.role_frequency_report_card).setOnClickListener {
            Toast.makeText(context, "Role Frequency Report", Toast.LENGTH_SHORT).show()
        }
        
        view.findViewById<CardView>(R.id.non_participating_report_card).setOnClickListener {
            Toast.makeText(context, "Non-Participating Report", Toast.LENGTH_SHORT).show()
        }
        
        view.findViewById<CardView>(R.id.backout_members_report_card).setOnClickListener {
            Toast.makeText(context, "Backout Members Report", Toast.LENGTH_SHORT).show()
        }
        
        view.findViewById<CardView>(R.id.attendance_report_card).setOnClickListener {
            Toast.makeText(context, "Attendance Report", Toast.LENGTH_SHORT).show()
        }
        
        view.findViewById<CardView>(R.id.performance_report_card).setOnClickListener {
            Toast.makeText(context, "Performance Report", Toast.LENGTH_SHORT).show()
        }
    }
} 