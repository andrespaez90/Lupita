package com.example.lupita.ui.factories

import android.view.ViewGroup
import com.example.lupita.ui.adapters.list.models.GenericItemView
import com.example.lupita.ui.items.ImageTextItem
import com.example.lupita.ui.items.ProductViewItem
import com.example.lupita.ui.items.SimpleVectorCompatTextView

const val ITEM_GENERAL_SELECTOR = 1001
const val ITEM_IMAGE_SELECTOR = 1002
const val ITEM_PRODUCT_SELECTOR = 1003

open class AppListFactory(
    private var listener: ((view: Any) -> Unit)? = null
) : GenericAdapterFactory() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericItemView<*> {
        return when (viewType) {
            ITEM_PRODUCT_SELECTOR -> ProductViewItem(parent.context).apply {
                setOnClickListener { listener?.invoke(data) }
            }
            ITEM_IMAGE_SELECTOR -> ImageTextItem(parent.context).apply {
                setOnClickListener { listener?.invoke(it) }
            }
            else -> SimpleVectorCompatTextView(parent.context).apply {
                setOnClickListener { listener?.invoke(it) }
            }
        }
    }

    fun setListener(newListener: (data: Any) -> Unit) {
        this.listener = newListener
    }
}