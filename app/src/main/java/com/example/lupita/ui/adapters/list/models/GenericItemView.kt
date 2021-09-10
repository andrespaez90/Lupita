package com.example.lupita.ui.adapters.list.models

import android.view.View

interface GenericItemView<T> {

    val data: T

    fun bind(var1: T)

    fun setSelected(var1: Boolean)

    fun getView(): View? = null

    fun binds(data: Any?) {
        bind(data as T)
    }
}
