package com.example.tmdp.ui.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tmdp.R
import com.example.tmdp.common.AppConstant
import com.example.tmdp.common.BaseRecyclerViewAdapter
import com.example.tmdp.common.GlideUtils
import com.example.tmdp.common.Utils.openURL
import com.example.tmdp.common.Utils.showToast
import com.example.tmdp.databinding.FragmentMovieDetailBinding
import com.example.tmdp.databinding.ListItemTrailerBinding
import com.example.tmdp.model.Movie
import com.example.tmdp.model.MovieVideo

class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private lateinit var viewModel: MovieDetailViewModel

    lateinit var trailerMovieAdapter: BaseRecyclerViewAdapter<MovieVideo>
    lateinit var currentMovie: Movie

    private var isFavourite: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        currentMovie = arguments?.getSerializable("movie") as Movie
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)

        initUI()

        isSaved()
        binding.fabFavorite.setOnClickListener {
            saveFavorite()
        }

        viewModel.getMovieTrailers(currentMovie.id)

    }


    private fun initUI() {
        binding.apply {
            tvTitle.text = currentMovie.title
            tvDate.text = currentMovie.release_date
            tvOverview.text = currentMovie.overview
            tvVote.text = currentMovie.vote_average.toString()
        }

        GlideUtils.showImage(
            AppConstant.TMDB_IMAGE_URL + currentMovie.poster_path,
            R.drawable.ic_launcher_foreground,
            binding.imageView
        )

        trailerMovieAdapter = BaseRecyclerViewAdapter(R.layout.list_item_trailer)
        { item, view -> bindViewHolder(item, view) }
        binding.recyclerviewTrailer.adapter = trailerMovieAdapter

        viewModel.movieTrailers.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                trailerMovieAdapter.setItem(it)
            } else {
                binding.recyclerviewTrailer.visibility = View.GONE
            }
        }
    }

    private fun isSaved() {
        viewModel.getSingleMovie(currentMovie.id).observe(viewLifecycleOwner) {
            isFavourite = if (it != null) {
                binding.fabFavorite.setImageResource(R.drawable.ic_favorite)
                true
            } else {
                binding.fabFavorite.setImageResource(R.drawable.ic_favorite_border)
                false
            }
        }
    }

    private fun saveFavorite() {
        if (isFavourite != true) {
            viewModel.insertMovie(currentMovie)
            showToast("Added to favorites")
        } else {
            viewModel.deleteMovie(currentMovie)
            showToast("Removed from favorites")
        }
    }

    private fun bindViewHolder(item: MovieVideo, view: View) {
        val itemBinding = ListItemTrailerBinding.bind(view)
        itemBinding.tvTrailerTitle.text = item.name
        itemBinding.root.setOnClickListener {
            openURL(requireContext(), AppConstant.YOUTUBE_URL + item.key)
        }

    }

}