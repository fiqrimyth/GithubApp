package com.example.githubapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapp.R
import com.example.githubapp.model.AllUser
import com.squareup.picasso.Picasso

class SearchAdapter(private val items: List<AllUser>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.search_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = items[position]

        Picasso.get().load(user.items[position].avatarURL).into(holder.imgPhoto)
        holder.tvName.text = user.items[position].login
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.ivUser)
        var imgPhoto: ImageView = itemView.findViewById(R.id.ivUser)
    }
}
