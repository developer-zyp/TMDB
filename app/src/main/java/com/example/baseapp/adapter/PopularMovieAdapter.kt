package com.example.baseapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.baseapp.R
import com.example.baseapp.common.AppConstant
import com.example.baseapp.databinding.ListItemMovieBinding
import com.example.baseapp.model.Movie

class PopularMovieAdapter :
    RecyclerView.Adapter<PopularMovieAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)


    private val differCallBack = object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem == newItem
    }

    private val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListItemMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(AppConstant.TMDB_IMAGE_URL + movie.poster_path)
                .into(holder.binding.itemMovieImage)
            // Picasso.get().load(IMAGE_BASE_URL + movie.poster_path).into(holder.binding.ivMovieImage)
            holder.binding.itemMovieName.text = movie.title
//            holder.binding.itemReleaseDate.text = movie.release_date
//            holder.binding.tvMovieVote.text = movie.vote_average.toString()
            setOnClickListener {
                val bundle = bundleOf("movie_details" to movie)
                Navigation.findNavController(it)
                    .navigate(R.id.action_listFragment_to_detailFragment, bundle)
                onItemClickListener?.let { it(movie) }
            }
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Movie) -> Unit)? = null

    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener
    }

}