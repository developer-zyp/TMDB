package com.example.tmdp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdp.R
import com.example.tmdp.model.Movie

//import kotlinx.android.synthetic.main.list_item_movie.view.*

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var movieList = emptyList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.itemView.item_movie_name.text = movieList[position].title
//
//        Glide.with(AppInstance.getInstance())
//            .load("https://image.tmdb.org/t/p/w185/${movieList[position].poster_path}")
//            .placeholder(R.drawable.ic_launcher_foreground)
//            .into(holder.itemView.item_movie_image)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setList(list: List<Movie>) {
        movieList = list
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
//            MainFragment.clickMovie(movieList[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        holder.itemView.setOnClickListener(null)
    }
}