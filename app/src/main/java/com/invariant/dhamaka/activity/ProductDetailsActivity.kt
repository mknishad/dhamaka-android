package com.invariant.dhamaka.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.invariant.dhamaka.R
import com.invariant.dhamaka.databinding.ActivityProductDetailsBinding
import com.invariant.dhamaka.viewmodel.ProductDetailsViewModel

class ProductDetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    private lateinit var viewModel: ProductDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_details)
        viewModel = ViewModelProviders.of(this).get(ProductDetailsViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}
