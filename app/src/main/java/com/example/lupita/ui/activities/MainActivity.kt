package com.example.lupita.ui.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lupita.R
import com.example.lupita.databinding.ActivityMainBinding
import com.example.lupita.network.models.SeekerProduct
import com.example.lupita.ui.adapters.list.GenericAdapter
import com.example.lupita.ui.adapters.list.models.GenericItemAbstract
import com.example.lupita.ui.factories.AppListFactory
import com.example.lupita.ui.factories.ITEM_PRODUCT_SELECTOR
import com.example.lupita.ui.items.DividerItemDecoration
import com.example.lupita.viewModels.MainViewModel
import com.example.lupita.viewModels.extensions.provideViewModel

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        binding.lifecycleOwner = this
        initView()
        initViewModel()
    }

    /**
     * init View
     */

    private fun initView() {
        binding.recyclerViewList.run {
            adapter = GenericAdapter(AppListFactory())
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    resources.getDimensionPixelSize(
                        R.dimen.spacing_small
                    )
                )
            )
        }
        binding.layoutToolbar.setSearchHint(R.string.search_hint_product)
        initListeners()
    }

    private fun initListeners() {
        //binding.layoutRefresh.setOnRefreshListener { updateInformationView() }
        // binding.layoutToolbar.setScanListener { startActivity(StartActivityModel(ScanProductActivity::class.java)) }
        binding.layoutToolbar.onSearchChange { viewModel.findProduct(it) }
    }

    /**
     * Init ViewMdoel
     */

    private fun initViewModel() {
        viewModel = provideViewModel()
        subscribeViewModel(viewModel, binding.root)
        viewModel.onSeekerProductChange().observe(this, Observer { updateView(it) })
    }

    private fun updateView(products: List<SeekerProduct>) {
        (binding.recyclerViewList.adapter as GenericAdapter).setItems(
            products.map {
                GenericItemAbstract(it, ITEM_PRODUCT_SELECTOR)
            }
        )
    }

}