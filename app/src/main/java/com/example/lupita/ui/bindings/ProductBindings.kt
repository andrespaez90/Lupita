package com.example.lupita.ui.bindings


import android.text.SpannableStringBuilder
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.color
import androidx.databinding.BindingAdapter
import com.example.lupita.R
import com.example.lupita.network.models.SeekerProduct
import com.example.lupita.ui.extensions.fontSize

object ProductBindings {

    @JvmStatic
    @BindingAdapter("product_description")
    fun productTitle(textView: TextView, product: SeekerProduct?) {
        product?.let {
            textView.text = SpannableStringBuilder()
                .fontSize(textView.context, R.dimen.font_subtitle) {
                    bold { appendln(product.name) }
                        .color(R.color.colorAccent) {
                            fontSize(textView.context, R.dimen.font_body) {
                                append(product.price)
                            }
                        }
                }
        }
    }
}