package com.bnt.toastmasters.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.widget.ImageView
import androidx.cardview.widget.CardView
import android.graphics.Color
import com.bnt.toastmasters.R

class UpcomingMeetingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_upcoming_meetings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val recyclerView = view.findViewById<RecyclerView>(R.id.upcoming_meetings_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = UpcomingMeetingsAdapter(getMockUpcomingMeetingsData())
    }

    private fun getMockUpcomingMeetingsData(): List<UpcomingMeetingItem> {
        return listOf(
            UpcomingMeetingItem("Weekly Meeting", "2024-01-25", "7:00 PM", "Conference Room A", "John Smith", R.drawable.ic_meeting),
            UpcomingMeetingItem("Contest Preparation", "2024-01-28", "6:30 PM", "Main Hall", "Sarah Johnson", R.drawable.ic_contest),
            UpcomingMeetingItem("Workshop: Public Speaking", "2024-02-01", "5:00 PM", "Training Room", "Mike Davis", R.drawable.ic_workshop),
            UpcomingMeetingItem("Monthly Evaluation", "2024-02-05", "7:30 PM", "Conference Room B", "Emily Wilson", R.drawable.ic_meeting)
        )
    }
}

data class UpcomingMeetingItem(
    val title: String,
    val date: String,
    val time: String,
    val location: String,
    val organizer: String,
    val meetingIcon: Int
)

class UpcomingMeetingsAdapter(private val items: List<UpcomingMeetingItem>) :
    RecyclerView.Adapter<UpcomingMeetingsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.meeting_title_text)
        val dateText: TextView = view.findViewById(R.id.meeting_date_text)
        val timeText: TextView = view.findViewById(R.id.meeting_time_text)
        val locationText: TextView = view.findViewById(R.id.meeting_location_text)
        val organizerText: TextView = view.findViewById(R.id.meeting_organizer_text)
        val meetingIcon: ImageView = view.findViewById(R.id.meeting_icon)
        val cardView: CardView = view.findViewById(R.id.upcoming_meeting_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_upcoming_meeting, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        
        holder.titleText.text = item.title
        holder.dateText.text = item.date
        holder.timeText.text = item.time
        holder.locationText.text = item.location
        holder.organizerText.text = "Organized by: ${item.organizer}"
        holder.meetingIcon.setImageResource(item.meetingIcon)
        
        // Set background color based on meeting type
        when {
            item.title.contains("Contest") -> holder.cardView.setCardBackgroundColor(Color.parseColor("#E3F2FD")) // Blue
            item.title.contains("Workshop") -> holder.cardView.setCardBackgroundColor(Color.parseColor("#F3E5F5")) // Purple
            else -> holder.cardView.setCardBackgroundColor(Color.WHITE)
        }
    }

    override fun getItemCount() = items.size
} 