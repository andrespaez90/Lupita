package com.example.lupita.ui.adapters.list.models

import com.example.lupita.ui.factories.GenericAdapterFactory.TYPE_CATEGORY

class CategoryItem(
    override var data: CharSequence,
    override var weight: Int,
) : GenericCategoryItem<CharSequence> {

    override val type: Int
        get() = TYPE_CATEGORY

    override val categoryName: String
        get() = data.toString()

    override fun compareTo(categoryItem: GenericCategoryItem<*>): Int {
        return categoryName.compareTo(categoryItem.categoryName)
    }
}