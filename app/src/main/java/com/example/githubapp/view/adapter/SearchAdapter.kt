package com.example.githubapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapp.R
import com.example.githubapp.model.Item

class SearchAdapter(private val context: Context) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private var mItems: ArrayList<Item> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.search_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val user = mItems[position]
            Glide.with(context).load(user.avatarURL).into(holder.imgPhoto)
            holder.tvName.text = user.login
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun putItems(items: ArrayList<Item>) {
        mItems.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        mItems.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tvUser)
        var imgPhoto: ImageView = itemView.findViewById(R.id.ivUser)
    }
}