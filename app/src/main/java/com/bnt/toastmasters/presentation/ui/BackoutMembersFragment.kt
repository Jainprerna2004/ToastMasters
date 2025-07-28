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

class BackoutMembersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_backout_members, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val recyclerView = view.findViewById<RecyclerView>(R.id.backout_members_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = BackoutMembersAdapter(getMockBackoutMembersData())
    }

    private fun getMockBackoutMembersData(): List<BackoutMemberItem> {
        return listOf(
            BackoutMemberItem("John Smith", "Level 3", "2 meetings", "2024-01-15", R.drawable.ic_profile),
            BackoutMemberItem("Sarah Johnson", "Level 2", "1 meeting", "2024-01-10", R.drawable.ic_profile),
            BackoutMemberItem("Mike Davis", "Level 4", "3 meetings", "2024-01-20", R.drawable.ic_profile),
            BackoutMemberItem("Emily Wilson", "Level 1", "1 meeting", "2024-01-05", R.drawable.ic_profile)
        )
    }
}

data class BackoutMemberItem(
    val name: String,
    val level: String,
    val backoutCount: String,
    val lastBackoutDate: String,
    val profileIcon: Int
)

class BackoutMembersAdapter(private val items: List<BackoutMemberItem>) :
    RecyclerView.Adapter<BackoutMembersAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.member_name_text)
        val levelText: TextView = view.findViewById(R.id.member_level_text)
        val backoutCountText: TextView = view.findViewById(R.id.backout_count_text)
        val lastBackoutDateText: TextView = view.findViewById(R.id.last_backout_date_text)
        val profileIcon: ImageView = view.findViewById(R.id.profile_icon)
        val cardView: CardView = view.findViewById(R.id.backout_member_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_backout_member, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        
        holder.nameText.text = item.name
        holder.levelText.text = item.level
        holder.backoutCountText.text = item.backoutCount
        holder.lastBackoutDateText.text = "Last: ${item.lastBackoutDate}"
        holder.profileIcon.setImageResource(item.profileIcon)
        
        // Set background color based on backout count
        when {
            item.backoutCount.contains("3") -> holder.cardView.setCardBackgroundColor(Color.parseColor("#FFEBEE")) // Red
            item.backoutCount.contains("2") -> holder.cardView.setCardBackgroundColor(Color.parseColor("#FFF3E0")) // Orange
            else -> holder.cardView.setCardBackgroundColor(Color.WHITE)
        }
    }

    override fun getItemCount() = items.size
} 