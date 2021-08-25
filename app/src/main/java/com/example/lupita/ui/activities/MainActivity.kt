package com.example.lupita.ui.activities

import android.os.Bundle
import android.view.Gravity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lupita.R
import com.example.lupita.databinding.ActivityMainBinding
import com.example.lupita.network.models.SeekerProduct
import com.example.lupita.network.models.SitesCategories
import com.example.lupita.ui.adapters.list.GenericAdapter
import com.example.lupita.ui.adapters.list.models.GenericItemAbstract
import com.example.lupita.ui.factories.AppListFactory
import com.example.lupita.ui.factories.ITEM_GENERAL_SELECTOR
import com.example.lupita.ui.factories.ITEM_PRODUCT_SELECTOR
import com.example.lupita.ui.items.DividerItemDecoration
import com.example.lupita.ui.items.models.DrawableSimpleTextView
import com.example.lupita.ui.items.models.SpacingSimpleTextView
import com.example.lupita.ui.items.models.VectorTextParams
import com.example.lupita.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

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
            adapter = GenericAdapter(AppListFactory {
                if (it is SeekerProduct) viewModel.onProductClicked(it)
            })
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                DividerItemDecoration(
                    resources.getDimensionPixelSize(
                        R.dimen.spacing_micro
                    )
                )
            )
        }
        binding.layoutToolbar.setSearchHint(R.string.search_hint_product)
        initListeners()
    }

    private fun initListeners() {
        binding.layoutRefresh.setOnRefreshListener { viewModel.getCategories() }
        binding.layoutToolbar.onSearchChange { viewModel.findProduct(it) }
    }

    /**
     * Init ViewMdoel
     */

    private fun initViewModel() {
        binding.viewModel = viewModel
        subscribeViewModel(viewModel, binding.root)
        viewModel.onSeekerProductChange().observe(this, Observer { updateView(it) })
        viewModel.onCategoriesChange().observe(this, Observer { updateCategories(it) })
    }

    private fun updateView(products: List<SeekerProduct>) {
        binding.recyclerViewList.layoutManager = LinearLayoutManager(this)
        (binding.recyclerViewList.adapter as GenericAdapter).setItems(
            products.map {
                GenericItemAbstract(it, ITEM_PRODUCT_SELECTOR)
            }
        )
    }

    private fun updateCategories(categories: List<SitesCategories>) {
        binding.recyclerViewList.layoutManager = GridLayoutManager(this, 2)
        (binding.recyclerViewList.adapter as GenericAdapter).setItems(
            categories.map { data ->
                GenericItemAbstract(
                    VectorTextParams(data.name).apply {
                        padding = SpacingSimpleTextView(R.dimen.spacing_large)
                        backgroundColor = R.color.white
                        textColor = R.color.dark_gray
                        gravity = Gravity.CENTER
                        drawable = DrawableSimpleTextView(drawableTop = R.drawable.ic_apartment)
                        fontSize = R.dimen.font_h4
                    },
                    ITEM_GENERAL_SELECTOR
                )
            }
        )
    }

    /**
     * Override
     */

    override fun showLoading(showing: Boolean) {
        binding.layoutRefresh.isRefreshing = showing
    }

}