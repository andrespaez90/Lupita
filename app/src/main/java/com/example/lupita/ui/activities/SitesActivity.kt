package com.example.lupita.ui.activities

import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lupita.R
import com.example.lupita.databinding.ActivitySitesBinding
import com.example.lupita.network.models.CountryAvailable
import com.example.lupita.ui.adapters.list.GenericAdapter
import com.example.lupita.ui.adapters.list.models.GenericItemAbstract
import com.example.lupita.ui.factories.AppListFactory
import com.example.lupita.ui.factories.ITEM_IMAGE_SELECTOR
import com.example.lupita.ui.items.DividerItemDecoration
import com.example.lupita.ui.items.models.SpacingSimpleTextView
import com.example.lupita.ui.items.models.VectorTextParams
import com.example.lupita.viewModels.SelectSiteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SitesActivity : BaseActivity() {

    private val itemsFactory = AppListFactory()

    private lateinit var binding: ActivitySitesBinding

    private val viewModel: SelectSiteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sites)
        binding.lifecycleOwner = this
        initView()
        initViewModel()
        initListeners()
    }

    /**
     * Init View
     */

    private fun initView() {
        setupActionBar(binding.toolbar, false)
        binding.recyclerViewList.run {
            adapter = GenericAdapter(itemsFactory)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(resources.getDimensionPixelSize(R.dimen.spacing_small)))
        }
    }

    /**
     * Init View Model
     */

    private fun initViewModel() {
        subscribeViewModel(viewModel, binding.root)
        viewModel.onDataChange().observe(this, Observer { updateInformation(it) })
    }

    private fun updateInformation(sites: List<CountryAvailable>) {
        (binding.recyclerViewList.adapter as GenericAdapter).items =
            sites.map {
                GenericItemAbstract(
                    Pair(getString(R.string.image_url, it.countryCode),
                        VectorTextParams(it.name).apply {
                            padding = SpacingSimpleTextView(R.dimen.spacing_large)
                            backgroundColor = R.color.white
                            textColor = R.color.black
                            gravity = Gravity.LEFT
                            tag = it.id
                        }),
                    ITEM_IMAGE_SELECTOR
                )
            }
    }

    /**
     * init Listeners
     */

    private fun initListeners() {
        binding.layoutRefresh.setOnRefreshListener { viewModel.updateInformation() }
        itemsFactory.setListener { if (it is View) viewModel.setCountry(it.tag) }
    }

    /**
     * Override
     */

    override fun showLoading(showing: Boolean) {
        binding.layoutRefresh.isRefreshing = showing
    }
}