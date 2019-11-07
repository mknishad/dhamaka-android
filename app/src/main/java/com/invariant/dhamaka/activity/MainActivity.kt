package com.invariant.dhamaka.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.invariant.dhamaka.R
import com.invariant.dhamaka.adapter.ProductAdapter
import com.invariant.dhamaka.adapter.ProductListener
import com.invariant.dhamaka.databinding.ActivityMainBinding
import com.invariant.dhamaka.model.Product
import com.invariant.dhamaka.util.Constants
import com.invariant.dhamaka.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.wtf

class MainActivity : BaseActivity(), AnkoLogger {

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
        setupAllProductsRecyclerView()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupCarouselView() {
        carouselImageUrls.add(
            "https://www.insurance2go.co.uk/media/1677/iphone-11.png?anchor=" +
                    "center&mode=crop&width=390&height=360&rnd=132132898460000000"
        )
        carouselImageUrls.add("https://brain-images-ssl.cdn.dixons.com/0/2/10187920/u_10187920.jpg")
        carouselImageUrls.add(
            "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/" +
                    "is/ipad-pro-11-select-wifi-spacegray-201810?wid=470&hei=556&fmt=png-alpha&." +
                    "v=1540591907088"
        )
        carouselImageUrls.add(
            "https://www.att.com/catalog/en/idse/Apple/Apple%20Watch%20Series%" +
                    "205%20-%2040mm/Space%20Black%20Stainless%20-%20Black%20Sport-hero-zoom.png"
        )
        carouselImageUrls.add(
            "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/imacpro-27-ret" +
                    "ina-config-hero?wid=412&hei=352&fmt=jpeg&qlt=95&.v=1512501389796"
        )

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

    private fun setupAllProductsRecyclerView() {
        val adapter = ProductAdapter(ProductListener { product ->
            startActivity<ProductDetailsActivity>(
                Constants.PRODUCT_ID to product._id
            )
        })

        adapter.addHeaderAndSubmitList(
            listOf(
                Product(
                    "1", "iPhone 11 Pro", 5.0f, 1100.0, 1050.0,
                    "https://www.insurance2go.co.uk/media/1677/iphone-11.png?anchor=" +
                            "center&mode=crop&width=390&height=360&rnd=132132898460000000"
                ),
                Product(
                    "2", "MacBook Pro", 5.0f, 1200.0, 1150.0,
                    "https://brain-images-ssl.cdn.dixons.com/0/2/10187920/u_10187920.jpg"
                ),
                Product(
                    "3", "iPad Pro", 5.0f, 1000.0, 950.0,
                    "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is" +
                            "/ipad-pro-11-select-wifi-spacegray-201810?wid=470&hei=556&fmt=png-a" +
                            "lpha&.v=1540591907088"
                ),
                Product(
                    "4", "Apple Watch Series 5", 5.0f, 500.0,
                    450.0,
                    "https://www.att.com/catalog/en/idse/Apple/Apple%20Watch%20Series%20" +
                            "5%20-%2040mm/Space%20Black%20Stainless%20-%20Black%20Sport-hero-zoom.png"
                ),
                Product(
                    "5", "iMac Pro", 5.0f, 1500.0,
                    1450.0,
                    "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is" +
                            "/imacpro-27-retina-config-hero?wid=412&hei=352&fmt=jpeg&qlt=95&.v=1" +
                            "512501389796"
                ),
                Product(
                    "1", "iPhone 11 Pro", 5.0f, 1100.0, 1050.0,
                    "https://www.insurance2go.co.uk/media/1677/iphone-11.png?anchor=" +
                            "center&mode=crop&width=390&height=360&rnd=132132898460000000"
                ),
                Product(
                    "2", "MacBook Pro", 5.0f, 1200.0, 1150.0,
                    "https://brain-images-ssl.cdn.dixons.com/0/2/10187920/u_10187920.jpg"
                ),
                Product(
                    "3", "iPad Pro", 5.0f, 1000.0, 950.0,
                    "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is" +
                            "/ipad-pro-11-select-wifi-spacegray-201810?wid=470&hei=556&fmt=png-a" +
                            "lpha&.v=1540591907088"
                ),
                Product(
                    "4", "Apple Watch Series 5", 5.0f, 500.0,
                    450.0,
                    "https://www.att.com/catalog/en/idse/Apple/Apple%20Watch%20Series%20" +
                            "5%20-%2040mm/Space%20Black%20Stainless%20-%20Black%20Sport-hero-zoom.png"
                ),
                Product(
                    "5", "iMac Pro", 5.0f, 1500.0,
                    1450.0,
                    "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is" +
                            "/imacpro-27-retina-config-hero?wid=412&hei=352&fmt=jpeg&qlt=95&.v=1" +
                            "512501389796"
                )
            )
        )

        val manager = GridLayoutManager(this, 3)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) = when (position) {
                0 -> 3
                else -> 1
            }
        }
        binding.allProductsRecyclerView.layoutManager = manager
        binding.allProductsRecyclerView.adapter = adapter
        //ViewCompat.setNestedScrollingEnabled(binding.allProductsRecyclerView, false)
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
