package com.example.lupita.ui.items

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Switch
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class DividerItemDecoration(
    private var space: Int,
    private var sides: Int = 0,
    private var spaceTopFirstItem: Int = 0,
    private var spaceBottomLastItem: Int = 0,
    private var divider: Drawable? = null,
) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = sides
        outRect.right = sides
        outRect.bottom = space

        when(parent.getChildLayoutPosition(view)){
            0 -> outRect.top = spaceTopFirstItem
            state.itemCount - 1 -> outRect.bottom = spaceBottomLastItem
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        divider?.let { drawCustomDivider(c, parent, state) } ?: run { super.onDrawOver(c, parent, state) }
    }

    private fun drawCustomDivider(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        parent.children.iterator().forEach {
            val params = it.layoutParams as RecyclerView.LayoutParams
            val top = it.bottom + params.bottomMargin
            val bottom = top + divider!!.intrinsicHeight
            divider!!.setBounds(left, top, right, bottom)
            divider!!.draw(c)
        }
    }
}