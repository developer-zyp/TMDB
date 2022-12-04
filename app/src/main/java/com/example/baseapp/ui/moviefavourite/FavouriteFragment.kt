package com.example.baseapp.ui.moviefavourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.baseapp.R
import com.example.baseapp.common.BaseRecyclerViewAdapter
import com.example.baseapp.common.AppConstant
import com.example.baseapp.databinding.FragmentFavouriteBinding
import com.example.baseapp.databinding.ListItemMovieBinding
import com.example.baseapp.model.Movie
import com.example.baseapp.ui.moviedetail.MovieDetailViewModel
import com.example.baseapp.common.GlideUtils

class FavouriteFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var viewModel: MovieDetailViewModel

    lateinit var favoriteMovieAdapter: BaseRecyclerViewAdapter<Movie>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)

        initUI()
        viewModel.getAllMovies().observe(viewLifecycleOwner) {
            favoriteMovieAdapter.setItem(it)
        }

    }

    private fun initUI() {
        favoriteMovieAdapter = BaseRecyclerViewAdapter(R.layout.list_item_fav)
        { item, view -> bindViewHolder(item, view) }
        binding.rvFavMovie.adapter = favoriteMovieAdapter
    }

    private fun bindViewHolder(item: Movie, view: View) {
        val itemMovieBinding = ListItemMovieBinding.bind(view)

        itemMovieBinding.itemMovieName.text = item.title
        GlideUtils.showImage(
            AppConstant.TMDB_IMAGE_URL + item.poster_path,
            android.R.drawable.ic_menu_gallery,
            itemMovieBinding.itemMovieImage
        )

        itemMovieBinding.itemMovieCard.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("movie", item)
            Navigation.findNavController(it)
                .navigate(R.id.action_favouriteFragment_to_movieDetailFragment, bundle)
        }

    }

}