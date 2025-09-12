//package com.example.favorite.ui
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import com.example.favorite.databinding.ActivityFavoriteBinding
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class FavoriteActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val binding = ActivityFavoriteBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        supportFragmentManager.beginTransaction()
//            .replace(binding.fragmentContainer.id, FavoriteFragment())
//            .commit()
//    }
//}
