package com.example.tmdp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.tmdp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        setupBottomNav()

    }

    private fun setupBottomNav() {
        navController = Navigation.findNavController(this, R.id.nav_host)

        binding.apply {

            bottomNavigationView.setupWithNavController(navController)

            navController.addOnDestinationChangedListener { controller, destination, arguments ->
                bottomNavigationView.visibility = when (destination.id) {
                    R.id.movieDetailFragment -> View.GONE
                    else -> View.VISIBLE

                }
            }

        }

//        NavigationUI.setupActionBarWithNavController(this, navController)

    }

}