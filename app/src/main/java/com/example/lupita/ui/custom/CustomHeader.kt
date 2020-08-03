package com.example.lupita.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.StringRes
import androidx.core.widget.addTextChangedListener
import com.example.lupita.R
import com.example.lupita.databinding.LayoutAppHeaderBinding
import com.example.lupita.ui.extensions.hideKeyboard
import com.example.lupita.ui.extensions.showKeyBoard

class CustomHeader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var searchAction: ((String) -> Unit)? = null

    private var onScanAction: (() -> Unit)? = null

    private var binding: LayoutAppHeaderBinding =
        LayoutAppHeaderBinding.inflate(LayoutInflater.from(context), this, true)


    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        initListeners()
    }

    private fun initListeners() {
        binding.imageViewScan.setOnClickListener { onScanAction?.invoke() }
        binding.imageViewSearch.setOnClickListener { showSeeker() }
        binding.editTextQuery.addTextChangedListener(afterTextChanged = { searchAction?.invoke(it.toString()) })
        binding.editTextQuery.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    v.hideKeyboard()
                    return true
                }
                return false
            }
        })
    }

    private fun showSeeker() {
        if (binding.editTextQuery.visibility == View.GONE) {
            binding.editTextQuery.visibility = View.VISIBLE
            binding.editTextQuery.requestFocus()
            binding.editTextQuery.showKeyBoard()
            binding.imageViewSearch.setImageResource(R.drawable.ic_close)
        } else {
            binding.editTextQuery.visibility = View.GONE
            binding.editTextQuery.hideKeyboard()
            binding.editTextQuery.clearFocus()
            binding.editTextQuery.setText("")
            binding.imageViewSearch.setImageResource(R.drawable.ic_search)
        }
    }

    /**
     * Config
     */

    fun setText(text: String) {
        binding.textViewName.text = text
    }

    fun setSearchHint(@StringRes hintResource: Int) {
        binding.editTextQuery.hint = context.getString(hintResource)
    }

    /**
     * Listeners
     */

    fun onSearchChange(action: (String) -> Unit) {
        this.searchAction = action
    }

    fun setScanListener(action: () -> Unit) {
        this.onScanAction = action
    }

}