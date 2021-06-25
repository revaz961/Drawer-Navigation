package com.example.myapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.MenuAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.MenuItem
import com.example.myapplication.ui.BlackFragment
import com.example.myapplication.ui.GreenFragment
import com.example.myapplication.ui.gallery.GalleryFragment
import com.example.myapplication.ui.home.HomeFragment
import com.example.myapplication.ui.slideshow.SlideshowFragment
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MenuAdapter
    private val items = mutableListOf<MenuItem>()
    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }


    private fun init(){
        setSupportActionBar(binding.appBarMain.toolbar)
        addItems()
        initRecycler()

        binding.appBarMain.btnOpen.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.content_id, GalleryFragment()).commit()

    }

    private fun initRecycler() {
        adapter = MenuAdapter().apply {
            setItems(items)
            click = {
                when (it) {
                    0 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.content_id, GalleryFragment()).commit()
                    }
                    1 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.content_id, HomeFragment()).commit()
                    }
                    2 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.content_id, SlideshowFragment()).commit()
                    }
                    3 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.content_id, BlackFragment()).commit()
                    }
                    4 -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.content_id, GreenFragment()).commit()
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
        }
    }

    private fun observes(){
        viewModel.liveData.observe(this,{
            Snackbar.make(binding.root,it,Snackbar.LENGTH_LONG).show()
        })
    }
}