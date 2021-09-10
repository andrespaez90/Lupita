package com.example.lupita.ui.factories

import android.view.ViewGroup
import com.example.lupita.ui.adapters.list.models.GenericItemView

const val ITEM_LIST_TYPE_CATEGORY = 1001

abstract class GenericAdapterFactory {

    abstract fun onCreateViewHolder(var1: ViewGroup, viewType: Int): GenericItemView<*>
}