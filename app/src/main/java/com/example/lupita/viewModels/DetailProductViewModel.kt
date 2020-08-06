package com.example.lupita.viewModels

import android.os.Bundle
import androidx.hilt.lifecycle.ViewModelInject
import com.example.lupita.ui.activities.WEB_VIEW_URL_PARAM
import com.example.lupita.ui.activities.WebViewActivity
import com.example.lupita.viewModels.general.AndroidViewModel
import com.example.lupita.viewModels.models.StartActivityModel
import javax.inject.Inject

class DetailProductViewModel @ViewModelInject constructor() : AndroidViewModel() {

    fun openSource(url: String) {
        val bundle = Bundle()
        bundle.putString(WEB_VIEW_URL_PARAM, url)
        startActivity.postValue(StartActivityModel(WebViewActivity::class.java, bundle))
    }

}