package com.example.lupita.viewModels

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lupita.MainActivity
import com.example.lupita.data.CountrySelectedPreference
import com.example.lupita.managers.preferences.PrefsManager
import com.example.lupita.network.api.SitesApi
import com.example.lupita.network.models.Sites
import com.example.lupita.viewModels.general.AndroidViewModel
import com.example.lupita.viewModels.models.FinishActivityModel
import com.example.lupita.viewModels.models.StartActivityModel
import javax.inject.Inject

class SelectSiteViewModel @Inject constructor(
    private val sitesApi: SitesApi,
    private val prefsManager: PrefsManager
) : AndroidViewModel() {

    private val siteList = MutableLiveData<List<Sites>>()

    init {
        if (prefsManager.getString(CountrySelectedPreference()).isBlank()) {
            updateInformation()
        } else {
            launchHome()
        }
    }

    fun updateInformation() {
        disposables.add(sitesApi.getSites()
            .doOnSubscribe { showLoading() }
            .doFinally { hideLoading() }
            .subscribe({ siteList.postValue(it) }, ::showServiceError)
        )
    }

    /**
     * Actions
     */

    fun setCountry(tag: Any?) {
        if (tag is String) {
            prefsManager.set(CountrySelectedPreference(), tag)
            launchHome()
        }
    }

    private fun launchHome() {
        startActivity.postValue(StartActivityModel(MainActivity::class.java))
        closeView.postValue(FinishActivityModel(Activity.RESULT_OK))
    }

    /**
     * LiveData
     */

    fun onDataChange(): LiveData<List<Sites>> = siteList

}