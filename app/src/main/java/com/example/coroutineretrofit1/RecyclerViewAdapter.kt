package com.example.coroutineretrofit1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutineretrofit1.R
import com.example.coroutineretrofit1.model.RecyclerData
import com.example.coroutineretrofit1.model.RecylerList
import com.squareup.picasso.Picasso
import java.util.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var itemList = ArrayList<RecyclerData>()

    fun setItemList(items: List<RecyclerData>) {
        itemList as List<RecylerList>
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.imageView)
        val title = view.findViewById<TextView>(R.id.tvTitle)
        val desc = view.findViewById<TextView>(R.id.tvDesc)
        fun bind(data: RecyclerData) {
            title.text = data.name
            desc.text = data.description
            val url = data.owner.avatar_url
            Picasso.get().load(url).into(image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerviewlayout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList.get(position))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}