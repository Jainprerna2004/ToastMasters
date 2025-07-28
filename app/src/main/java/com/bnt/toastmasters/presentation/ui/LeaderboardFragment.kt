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

class LeaderboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_leaderboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val recyclerView = view.findViewById<RecyclerView>(R.id.leaderboard_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = LeaderboardAdapter(getMockLeaderboardData())
    }

    private fun getMockLeaderboardData(): List<LeaderboardItem> {
        return listOf(
            LeaderboardItem("John Smith", "Level 5", 1250, 1, R.drawable.ic_medal_gold),
            LeaderboardItem("Sarah Johnson", "Level 4", 1180, 2, R.drawable.ic_medal_silver),
            LeaderboardItem("Mike Davis", "Level 4", 1120, 3, R.drawable.ic_medal_bronze),
            LeaderboardItem("Emily Wilson", "Level 3", 1050, 4, R.drawable.ic_rank_4),
            LeaderboardItem("David Brown", "Level 3", 980, 5, R.drawable.ic_rank_5),
            LeaderboardItem("Lisa Anderson", "Level 2", 920, 6, R.drawable.ic_rank_6),
            LeaderboardItem("Tom Martinez", "Level 2", 870, 7, R.drawable.ic_rank_7),
            LeaderboardItem("Anna Garcia", "Level 1", 820, 8, R.drawable.ic_rank_8)
        )
    }
}

data class LeaderboardItem(
    val name: String,
    val level: String,
    val points: Int,
    val rank: Int,
    val medalIcon: Int
)

class LeaderboardAdapter(private val items: List<LeaderboardItem>) :
    RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rankText: TextView = view.findViewById(R.id.rank_text)
        val nameText: TextView = view.findViewById(R.id.name_text)
        val levelText: TextView = view.findViewById(R.id.level_text)
        val pointsText: TextView = view.findViewById(R.id.points_text)
        val medalIcon: ImageView = view.findViewById(R.id.medal_icon)
        val cardView: CardView = view.findViewById(R.id.leaderboard_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_leaderboard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        
        holder.rankText.text = "#${item.rank}"
        holder.nameText.text = item.name
        holder.levelText.text = item.level
        holder.pointsText.text = "${item.points} pts"
        
        // Set medal icon
        holder.medalIcon.setImageResource(item.medalIcon)
        
        // Set background color based on rank
        when (item.rank) {
            1 -> holder.cardView.setCardBackgroundColor(Color.parseColor("#FFF8E1")) // Gold
            2 -> holder.cardView.setCardBackgroundColor(Color.parseColor("#F5F5F5")) // Silver
            3 -> holder.cardView.setCardBackgroundColor(Color.parseColor("#FBE9E7")) // Bronze
            else -> holder.cardView.setCardBackgroundColor(Color.WHITE)
        }
    }

    override fun getItemCount() = items.size
} 