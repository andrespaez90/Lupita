package com.example.lupita.ui.adapters.list.models

import com.example.lupita.ui.factories.ITEM_LIST_TYPE_CATEGORY

class CategoryItem(
    override var data: CharSequence,
    override var weight: Int,
) : GenericCategoryItem<CharSequence> {

    override val type: Int
        get() = ITEM_LIST_TYPE_CATEGORY

    override val categoryName: String
        get() = data.toString()

    override fun compareTo(categoryItem: GenericCategoryItem<*>): Int {
        return categoryName.compareTo(categoryItem.categoryName)
    }
}