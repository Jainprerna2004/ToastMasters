package com.bnt.toastmasters.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.cardview.widget.CardView
import android.widget.Toast
import com.bnt.toastmasters.R

class CreateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Set up click listeners for the creation options
        view.findViewById<CardView>(R.id.create_meeting_card).setOnClickListener {
            // TODO: Navigate to meeting creation
            Toast.makeText(context, "Create Meeting", Toast.LENGTH_SHORT).show()
        }
        
        view.findViewById<CardView>(R.id.create_event_card).setOnClickListener {
            // TODO: Navigate to event creation
            Toast.makeText(context, "Create Event", Toast.LENGTH_SHORT).show()
        }
        
        view.findViewById<CardView>(R.id.create_contest_card).setOnClickListener {
            // TODO: Navigate to contest creation
            Toast.makeText(context, "Create Contest", Toast.LENGTH_SHORT).show()
        }
        
        view.findViewById<CardView>(R.id.create_workshop_card).setOnClickListener {
            // TODO: Navigate to workshop creation
            Toast.makeText(context, "Create Workshop", Toast.LENGTH_SHORT).show()
        }
    }
} 