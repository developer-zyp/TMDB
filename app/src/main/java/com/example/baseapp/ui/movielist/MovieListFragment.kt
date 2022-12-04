package com.example.baseapp.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.baseapp.R
import com.example.baseapp.common.AppConstant
import com.example.baseapp.common.BaseRecyclerViewAdapter
import com.example.baseapp.common.GlideUtils
import com.example.baseapp.common.Utils.hideProgressDialog
import com.example.baseapp.common.Utils.showProgressDialog
import com.example.baseapp.common.Utils.showToast
import com.example.baseapp.databinding.FragmentMovieListBinding
import com.example.baseapp.databinding.ListItemMovieBinding
import com.example.baseapp.model.Movie

class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var viewModel: MovieListViewModel

    lateinit var popularList: MutableList<Movie>
    lateinit var popularMovieAdapter: BaseRecyclerViewAdapter<Movie>
    lateinit var playingMovieAdapter: BaseRecyclerViewAdapter<Movie>
    lateinit var upcomingMovieAdapter: BaseRecyclerViewAdapter<Movie>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MovieListViewModel::class.java]

        popularList = mutableListOf()

        initUI()
        observeViewModel()

    }

    private fun initUI() {
//        binding.rvPopularMovie.layoutManager = GridLayoutManager(context, 3)
        popularMovieAdapter = BaseRecyclerViewAdapter(R.layout.list_item_movie)
        { item, view -> bindViewHolder(item, view) }
        binding.rvPopularMovie.adapter = popularMovieAdapter

        playingMovieAdapter = BaseRecyclerViewAdapter(R.layout.list_item_movie)
        { item, view -> bindViewHolder(item, view) }
        binding.rvPlayingMovie.adapter = playingMovieAdapter

        upcomingMovieAdapter = BaseRecyclerViewAdapter(R.layout.list_item_movie)
        { item, view -> bindViewHolder(item, view) }
        binding.rvUpcomingMovie.adapter = upcomingMovieAdapter


        binding.btnLoadMore.setOnClickListener {
//            viewModel.getPopularMovies()
//            findNavController().navigate(R.id.movieDetailFragment, null)
        }
    }

    private fun observeViewModel() {

        viewModel.getPopularMovies()
        viewModel.getPlayingMovies()
        viewModel.getUpcomingMovies()


        viewModel.popularMovie.observe(viewLifecycleOwner) {
            popularMovieAdapter.setItem(it)
        }

        viewModel.playingMovie.observe(viewLifecycleOwner) {
            playingMovieAdapter.setItem(it)
        }

        viewModel.upcomingMovie.observe(viewLifecycleOwner) {
            upcomingMovieAdapter.setItem(it)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            showToast(it)
        }

        viewModel.loading.observe(viewLifecycleOwner) {
//            if (it) {
//                showProgressDialog(requireContext(), "Loading ...")
//            } else {
//                hideProgressDialog()
//            }
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
//            val action = MovieListFragmentDirections.actionListFragmentToDetailFragment()
            findNavController().navigate(R.id.action_listFragment_to_detailFragment, bundle)
//            Navigation.findNavController(it)
//                .navigate(R.id.action_listFragment_to_detailFragment, bundle)
        }

    }


}