package com.example.marvelappapi.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.marvelappapi.R
import com.example.marvelappapi.data.Marvel
import com.example.marvelappapi.databinding.MarvelGridItemBinding

class MainRecyclerAdapter(val context: Context,
                                    val marvels: List<Marvel>,
                                    val itemListener: MarvelItemListener):
RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {
    override fun getItemCount() = marvels.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.marvel_grid_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val marvel = marvels[position]
        with(holder) {
            superhero_name.setText(marvel.superhero_name)
            // it.text = marvel.superhero_name
            /// it.contentDescription = marvel.superhero_name

        Glide.with(context)
                .load(marvel.imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .circleCrop()
                .into(photo)
            holder.itemView.setOnClickListener{
                itemListener.onMarvelItemClick(marvel)
            }
        }
    }

    inner class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
        val binding = MarvelGridItemBinding.bind(itemView)
        val superhero_name = itemView.findViewById<TextView>(R.id.superhero_name)
        val photo = itemView.findViewById<ImageView>(R.id.photo)
    }

    interface MarvelItemListener {
        fun onMarvelItemClick(marvel :Marvel)
    }
}


