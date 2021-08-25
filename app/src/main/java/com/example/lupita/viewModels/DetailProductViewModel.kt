package com.example.lupita.viewModels

import android.os.Bundle
import com.example.lupita.ui.activities.WEB_VIEW_URL_PARAM
import com.example.lupita.ui.activities.WebViewActivity
import com.example.lupita.viewModels.general.AndroidViewModel
import com.example.lupita.viewModels.models.StartActivityModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailProductViewModel @Inject constructor() : AndroidViewModel() {

    fun openSource(url: String) {
        val bundle = Bundle()
        bundle.putString(WEB_VIEW_URL_PARAM, url)
        startActivity.postValue(StartActivityModel(WebViewActivity::class.java, bundle))
    }
}