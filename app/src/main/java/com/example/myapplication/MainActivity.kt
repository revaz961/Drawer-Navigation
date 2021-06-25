package com.example.myapplication

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.MenuItem
import com.example.myapplication.ui.gallery.GalleryFragment
import com.example.myapplication.ui.home.HomeFragment
import com.example.myapplication.ui.slideshow.SlideshowFragment

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MenuAdapter
    private val items = mutableListOf<MenuItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addItems()
        initRecycler()

        setSupportActionBar(binding.appBarMain.toolbar)
    }

    private fun initRecycler() {
        adapter = MenuAdapter().apply {
            setItems(items)
            click = {
                when (it) {
                    0 -> {
                        // # Home Fragment
                        val homeFragment = GalleryFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_ccontent, homeFragment).commit()
                    }
                    1 -> {
                        // # Music Fragment
                        val musicFragment = HomeFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_ccontent, musicFragment).commit()
                    }
                    2 -> {
                        // # Movies Fragment
                        val moviesFragment = SlideshowFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_ccontent, moviesFragment).commit()
                    }
                    3 -> {
                        // # Books Fragment
                        val booksFragment = GalleryFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.activity_main_ccontent, booksFragment).commit()
                    }

                }
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
        binding.rvItems.adapter = adapter
        binding.rvItems.layoutManager = LinearLayoutManager(this)
    }

    private fun addItems() {
        items.apply {
            add(MenuItem(getString(R.string.home), R.drawable.ic_menu_gallery))
            add(MenuItem(getString(R.string.home), R.drawable.ic_menu_gallery))
            add(MenuItem(getString(R.string.home), R.drawable.ic_menu_gallery))
            add(MenuItem(getString(R.string.home), R.drawable.ic_menu_gallery))
            add(MenuItem(getString(R.string.home), R.drawable.ic_menu_gallery))
            add(MenuItem(getString(R.string.home), R.drawable.ic_menu_gallery))
        }
    }
}