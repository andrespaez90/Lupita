package com.example.lupita.ui.factories

import android.view.View
import android.view.ViewGroup
import com.example.lupita.ui.adapters.list.models.GenericItemView
import com.example.lupita.ui.items.ProductViewItem
import com.example.lupita.ui.items.SimpleVectorCompatTextView

const val ITEM_GENERAL_SELECTOR = 1001
const val ITEM_PRODUCT_SELECTOR = 1002

open class AppListFactory(private var listener: ((view: View) -> Unit)? = null) :
    GenericAdapterFactory() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericItemView<*> {
        return when (viewType) {
            ITEM_PRODUCT_SELECTOR -> ProductViewItem(parent.context)
            else -> SimpleVectorCompatTextView(parent.context).apply {
                setOnClickListener { listener?.invoke(it) }
            }
        }
    }

    fun setListener(newListener: (view: View) -> Unit) {
        this.listener = newListener
    }
}