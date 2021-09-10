package com.example.lupita.ui.adapters.list.models

interface GenericCategoryItem<T> : GenericItem<T> {

    val categoryName: String

    val weight: Int

    operator fun compareTo(var1: GenericCategoryItem<*>): Int
}