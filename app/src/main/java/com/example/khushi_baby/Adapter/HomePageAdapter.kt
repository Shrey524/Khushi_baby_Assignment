package com.example.khushi_baby.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.khushi_baby.Database.PaitentData
import com.example.khushi_baby.R

class HomePageAdapter(context: Context, list: ArrayList<PaitentData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
	private val context: Context = context
	var list: ArrayList<PaitentData> = list

	class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
		fun bind(position: Int) {

		}

	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return UserViewHolder(LayoutInflater.from(context).inflate(R.layout.user_item_view, parent, false))
	}


	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		(holder as UserViewHolder).bind(position)
	}

	override fun getItemCount(): Int {
		return list.size
	}
}