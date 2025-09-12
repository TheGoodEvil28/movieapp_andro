package com.example.favorite.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.domain.model.Movie
import com.example.core.ui.MovieAdapter
import com.example.favorite.R
import com.example.favorite.databinding.FragmentFavoriteBinding
import com.example.favorite.di.FavoriteModuleDependencies
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    // ✅ langsung inject dengan Hilt
    private val vm: FavoriteViewModel by viewModels()

    private val adapter = MovieAdapter(
        onClick = { movie -> },
        onFavoriteClick = { movie -> vm.toggleFavorite(movie) }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoriteBinding.bind(view)

        binding.rvFavorite.adapter = adapter
        binding.rvFavorite.layoutManager = LinearLayoutManager(requireContext())

        // ✅ observe data
        vm.favorites.observe(viewLifecycleOwner) { movies ->
            adapter.submitList(movies)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


private fun FavoriteViewModel.toggleFavorite(movie: Movie) {}
