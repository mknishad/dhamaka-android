package com.invariant.dhamaka.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.invariant.dhamaka.R
import com.invariant.dhamaka.databinding.ActivityMainBinding
import com.invariant.dhamaka.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import org.jetbrains.anko.wtf

class MainActivity : AppCompatActivity(), AnkoLogger {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var carouselImageUrls: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        init()
    }

    private fun init() {
        carouselImageUrls = ArrayList()
        setupToolbar()
        setupCarouselView()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupCarouselView() {
        carouselImageUrls.add("https://images.unsplash.com/photo-1471874276752-65e2d717604a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60")
        carouselImageUrls.add("https://images.unsplash.com/photo-1482066490729-6f26115b60dc?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=702&q=80")
        carouselImageUrls.add("https://images.unsplash.com/photo-1570963398800-a5c9dac212e9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=750&q=80")
        carouselImageUrls.add("https://images.unsplash.com/photo-1456926631375-92c8ce872def?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=750&q=80")
        carouselImageUrls.add("https://images.unsplash.com/photo-1515073883629-5e2924e3e106?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=750&q=80")

        binding.carouselView.pageCount = carouselImageUrls.size
        binding.carouselView.setImageListener { position, imageView ->
            wtf("position = $position, imageView = $imageView")
            Picasso.get()
                .load(carouselImageUrls[position])
                .placeholder(R.drawable.ic_toolbar_logo)
                .error(R.drawable.ic_toolbar_logo)
                .fit()
                .into(imageView)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cart -> {
                toast("Cart")
                true
            }
            R.id.action_search -> {
                toast("Search")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
