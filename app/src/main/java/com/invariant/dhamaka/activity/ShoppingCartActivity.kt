package com.invariant.dhamaka.activity

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.invariant.dhamaka.R
import com.invariant.dhamaka.adapter.CartListAdapter
import com.invariant.dhamaka.adapter.ProductListener
import com.invariant.dhamaka.databinding.ActivityShoppingCartBinding
import com.invariant.dhamaka.model.Product
import com.invariant.dhamaka.viewmodel.ShoppingCartViewModel
import org.jetbrains.anko.toast

class ShoppingCartActivity : BaseActivity() {

    private lateinit var binding: ActivityShoppingCartBinding
    private lateinit var viewModel: ShoppingCartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shopping_cart)
        viewModel = ViewModelProviders.of(this).get(ShoppingCartViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        init()
    }

    private fun init() {
        setupToolbar()
        setupCartRecyclerView()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.toolbar.setTitleTextColor(resources.getColor(android.R.color.white, null))
        } else {
            binding.toolbar.setTitleTextColor(resources.getColor(android.R.color.white))
        }
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.shoppint_cart)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            binding.toolbar.navigationIcon?.colorFilter =
                BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
        } else {
            binding.toolbar.navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
        }
    }

    private fun setupCartRecyclerView() {

        val adapter = CartListAdapter(ProductListener { product ->
            /*startActivity<ProductDetailsActivity>(
                Constants.PRODUCT_ID to product._id
            )*/
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

        binding.cartRecyclerView.adapter = adapter
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
