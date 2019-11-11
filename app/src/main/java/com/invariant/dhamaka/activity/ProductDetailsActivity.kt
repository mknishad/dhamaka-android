package com.invariant.dhamaka.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.invariant.dhamaka.R
import com.invariant.dhamaka.databinding.ActivityProductDetailsBinding
import com.invariant.dhamaka.viewmodel.ProductDetailsViewModel
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.wtf

class ProductDetailsActivity : BaseActivity(), AnkoLogger {

    private lateinit var binding: ActivityProductDetailsBinding
    private lateinit var viewModel: ProductDetailsViewModel
    private lateinit var carouselImageUrls: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_details)
        viewModel = ViewModelProviders.of(this).get(ProductDetailsViewModel::class.java)
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
}
