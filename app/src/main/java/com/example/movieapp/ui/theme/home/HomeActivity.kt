package com.example.movieapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.databinding.ActivityHomeBinding
import com.example.movieapp.ui.detail.DetailActivity
//import com.example.movieapp.ui.theme.favorite.FavoriteFragment
//import com.example.favorite.ui.FavoriteFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
//import com.example.favorite.ui.FavoriteFragment

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val vm: HomeViewModel by viewModels()
    private val adapter = MovieAdapter(
        onClick = { movie ->
            val i = Intent(this, DetailActivity::class.java)
            i.putExtra("movie_id", movie.id)
            startActivity(i)
        },
        onFavoriteClick = { movie ->
            vm.toggleFavorite(movie)
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovies.adapter = adapter

        // Observe movies
        lifecycleScope.launch {
            vm.movies.collectLatest { list ->
                adapter.submitList(list)
            }
        }

        // Favorites button
        binding.btnFavorites.setOnClickListener {
            binding.rvMovies.visibility = View.GONE
            binding.fragmentContainer.visibility = View.VISIBLE

            try {
                val clazz = Class.forName("com.example.favorite.ui.FavoriteFragment")
                val fragment = clazz.getConstructor().newInstance() as androidx.fragment.app.Fragment

                supportFragmentManager.beginTransaction()
                    .replace(binding.fragmentContainer.id, fragment)
                    .addToBackStack(null)
                    .commit()

            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            }
        }




    }

    override fun onBackPressed() {
        if (binding.fragmentContainer.isVisible) {
            binding.fragmentContainer.visibility = View.GONE
            binding.rvMovies.visibility = View.VISIBLE
        } else {
            super.onBackPressed()
        }
    }


//    private fun openFavorites() {
//        binding.rvMovies.visibility = View.GONE
//        binding.fragmentContainer.visibility = View.VISIBLE
//
//        try {
//            // Load class from dynamic feature module at runtime
//            val clazz = Class.forName("com.example.favorite.ui.FavoriteFragment")
//            val fragment = clazz.getConstructor().newInstance() as androidx.fragment.app.Fragment
//
//            supportFragmentManager.beginTransaction()
//                .replace(binding.fragmentContainer.id, fragment)
//                .addToBackStack(null)
//                .commit()
//
//        } catch (e: ClassNotFoundException) {
//            e.printStackTrace()
//        }
//    }

}
