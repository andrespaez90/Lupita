package com.example.lupita.ui.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.lupita.R
import com.example.lupita.databinding.ActivityDetailProductBinding
import com.example.lupita.network.models.SeekerProduct
import com.example.lupita.viewModels.DetailProductViewModel
import com.example.lupita.viewModels.extensions.provideViewModel

class DetailProductActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailProductBinding

    private lateinit var viewModel: DetailProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_product)
        val product: SeekerProduct? = intent.extras.getParcelable("product")
        product?.let {
            initView(it)
            initViewModel(it)
        } ?: run { finish() }

    }

    /**
     * Init View
     */

    private fun initView(product: SeekerProduct) {
        binding.lifecycleOwner = this
        binding.modelView = product
        setupActionBar(binding.toolbar)
        binding.textViewOpenSource.setOnClickListener { viewModel.openSource(product.link) }
    }

    /**
     * Init ViewModel
     */

    private fun initViewModel(product: SeekerProduct) {
        viewModel = provideViewModel()
        subscribeViewModel(viewModel, binding.root)
    }
}