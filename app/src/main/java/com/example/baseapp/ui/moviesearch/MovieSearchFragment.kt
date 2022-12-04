package com.example.baseapp.ui.moviesearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.baseapp.R
import com.example.baseapp.common.AppConstant
import com.example.baseapp.common.BaseRecyclerViewAdapter
import com.example.baseapp.common.GlideUtils
import com.example.baseapp.databinding.FragmentMovieSearchBinding
import com.example.baseapp.databinding.ListItemMovieBinding
import com.example.baseapp.model.Movie
import com.example.baseapp.ui.movielist.MovieListViewModel

class MovieSearchFragment : Fragment() {

    private lateinit var binding: FragmentMovieSearchBinding
    private lateinit var viewModel: MovieListViewModel

    lateinit var searchMovieAdapter: BaseRecyclerViewAdapter<Movie>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MovieListViewModel::class.java]

        searchMovieAdapter = BaseRecyclerViewAdapter(R.layout.list_item_fav)
        { item, view -> bindViewHolder(item, view) }
        binding.rvSearchMovie.adapter = searchMovieAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.isNotBlank()) {
                    viewModel.getSearchMovies(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        viewModel.searchMovies.observe(viewLifecycleOwner) {
            searchMovieAdapter.setItem(it)
        }


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
                .navigate(R.id.action_searchFragment_to_detailFragment, bundle)
        }

    }


}