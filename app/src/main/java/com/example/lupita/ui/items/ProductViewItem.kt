package com.example.lupita.ui.items

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.lupita.databinding.ItemSeekerProductBinding
import com.example.lupita.network.models.SeekerProduct
import com.example.lupita.ui.adapters.list.models.GenericItemView

class ProductViewItem(context: Context) : GenericItemView<SeekerProduct> {

    override lateinit var data: SeekerProduct

    private val binding = ItemSeekerProductBinding.inflate(LayoutInflater.from(context))

    init {
        binding.root.layoutParams =
            FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun bind(data: SeekerProduct) {
        this.data = data
        binding.modelView = this.data
    }

    override fun setSelected(isSelected: Boolean) {
        binding.root.isSelected = isSelected
    }

    override fun getView(): View = binding.root

    fun setOnClickListener(action: (SeekerProduct) -> Unit) {
        binding.root.setOnClickListener { action(data) }
    }
}