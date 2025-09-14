package com.example.movieapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.domain.model.Movie
import com.example.movieapp.databinding.ItemMovieBinding
import com.example.movieapp.R

class MovieAdapter(
    private val onClick: (Movie) -> Unit,
    private val onFavoriteClick: (Movie) -> Unit
) : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.tvTitle.text = movie.title
            binding.tvOverview.text = movie.overview

            Glide.with(binding.imgPoster.context)
                .load(movie.posterPath)
                .placeholder(android.R.drawable.ic_menu_report_image)
                .into(binding.imgPoster)

            // Click on the whole item opens detail
            binding.root.setOnClickListener { onClick(movie) }

            // Show favorite icon
            binding.btnFavorite.setImageResource(
                if (movie.isFavorite) R.drawable.ic_favorite_filled
                else R.drawable.ic_favorite_border
            )

            // Remove disabling
            binding.btnFavorite.isClickable = true
            binding.btnFavorite.isFocusable = true

// Handle favorite toggle
            binding.btnFavorite.setOnClickListener {
                onFavoriteClick(movie) // This calls HomeViewModel.toggleFavorite(movie)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
        }
    }
}
