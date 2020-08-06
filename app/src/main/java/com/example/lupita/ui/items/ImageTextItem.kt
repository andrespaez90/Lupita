package com.example.lupita.ui.items

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.databinding.adapters.ViewBindingAdapter.setPadding
import com.example.lupita.databinding.ItemImageTextBinding
import com.example.lupita.databinding.ItemSeekerProductBinding
import com.example.lupita.ui.adapters.list.models.GenericItemView
import com.example.lupita.ui.bindings.GeneralBindings.loadImage
import com.example.lupita.ui.extensions.applyDrawable
import com.example.lupita.ui.items.models.VectorTextParams

class ImageTextItem(private val context: Context) :
    GenericItemView<Pair<String, VectorTextParams>> {

    override lateinit var data: Pair<String, VectorTextParams>

    private var listener: ((view: Any) -> Unit)? = null

    private val binding = ItemImageTextBinding.inflate(LayoutInflater.from(context))

    init {
        binding.root.layoutParams =
            FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        binding.root.setOnClickListener { listener?.invoke(binding.textViewName) }
    }

    override fun bind(data: Pair<String, VectorTextParams>) {
        this.data = data
        loadImage(binding.imageViewCategory, data.first)
        initItem(data.second)
        binding.textViewName.applyDrawable(data.second.drawable)
        binding.textViewName.compoundDrawablePadding =
            context.resources.getDimensionPixelSize(data.second.padding.withDrawable)
        binding.textViewName.text =
            if (data.second.resourceId != 0) context.getString(data.second.resourceId) else data.second.text
        binding.textViewName.gravity = data.second.gravity
        binding.textViewName.tag = data.second.tag
    }

    private fun initItem(params: VectorTextParams) {
        setFontStyle(params)
        addPadding(params)
        addMargin(params)
        binding.textViewName.setBackgroundResource(params.backgroundColor)
    }

    private fun setFontStyle(item: VectorTextParams) {
        binding.textViewName.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            context.resources.getDimensionPixelSize(item.fontSize).toFloat()
        )
        binding.textViewName.setTextColor(ContextCompat.getColor(context, item.textColor))
    }

    private fun addPadding(item: VectorTextParams) {
        val top: Int = context.resources.getDimensionPixelSize(item.padding.spacingTop)
        val bottom: Int = context.resources.getDimensionPixelSize(item.padding.spacingBottom)
        val left: Int = context.resources.getDimensionPixelSize(item.padding.spacingLeft)
        val right: Int = context.resources.getDimensionPixelSize(item.padding.spacingRight)
        binding.textViewName.setPadding(left, top, right, bottom)
    }

    private fun addMargin(item: VectorTextParams) {
        val marginLayoutParams = binding.textViewName.layoutParams as ViewGroup.MarginLayoutParams
        marginLayoutParams.topMargin =
            context.resources.getDimensionPixelSize(item.margin.spacingTop)
        marginLayoutParams.bottomMargin =
            context.resources.getDimensionPixelSize(item.margin.spacingBottom)
        marginLayoutParams.leftMargin =
            context.resources.getDimensionPixelSize(item.margin.spacingLeft)
        marginLayoutParams.rightMargin =
            context.resources.getDimensionPixelSize(item.margin.spacingRight)
        binding.textViewName.layoutParams = marginLayoutParams
    }


    override fun setSelected(isSelected: Boolean) {
        binding.root.isSelected = isSelected
    }

    fun setOnClickListener(newListener: (data: Any) -> Unit) {
        this.listener = newListener
    }

    override fun getView(): View = binding.root

}