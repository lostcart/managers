package com.lost.managers.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.lost.managers.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val navController by lazy { findNavController(main_fragment_container.id) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        setupActionBarWithNavController(navController)
    }
}