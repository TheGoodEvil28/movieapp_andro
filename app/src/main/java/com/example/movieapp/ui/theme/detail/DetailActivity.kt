package com.example.movieapp.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val vm: DetailViewModel by viewModels()
    private var currentMovieId = -1
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentMovieId = intent.getIntExtra("movie_id", -1)
        if (currentMovieId != -1) vm.load(currentMovieId)

        // Collect detail movie and populate UI
        lifecycleScope.launch {
            vm.detail.collectLatest { movie ->
                movie?.let { m ->
                    // Basic info
                    binding.tvTitle.text = m.title
                    binding.tvOverview.text = m.overview
                    Glide.with(this@DetailActivity).load(m.posterPath).into(binding.imgPoster)

                    // Extra info
                    binding.tvReleaseDate.text = getString(R.string.release_date, m.releaseDate ?: "N/A")
                    binding.tvRating.text = getString(R.string.rating, m.rating ?: 0.0)
                    binding.tvGenre.text = "Genre: ${m.genres.joinToString(", ")}"

                    // Favorite button
                    isFavorite = m.isFavorite
                    updateFavoriteIcon(isFavorite)

                    binding.btnFavorite.setOnClickListener {
                        lifecycleScope.launch {
                            vm.toggleFavorite(m)
                            isFavorite = !isFavorite
                            updateFavoriteIcon(isFavorite)
                        }
                    }
                }
            }
        }
    }

    private fun updateFavoriteIcon(favorite: Boolean) {
        if (favorite) {
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite_filled)
        } else {
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite_border)
        }
    }
}
