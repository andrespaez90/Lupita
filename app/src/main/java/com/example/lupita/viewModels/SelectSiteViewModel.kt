package com.example.lupita.viewModels

import android.app.Activity
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lupita.ui.activities.MainActivity
import com.example.lupita.data.CountrySelectedPreference
import com.example.lupita.managers.preferences.PrefsManager
import com.example.lupita.network.api.SitesApi
import com.example.lupita.network.models.CountryAvailable
import com.example.lupita.network.models.CountryModel
import com.example.lupita.network.models.Sites
import com.example.lupita.viewModels.general.AndroidViewModel
import com.example.lupita.viewModels.models.FinishActivityModel
import com.example.lupita.viewModels.models.StartActivityModel
import javax.inject.Inject

class SelectSiteViewModel @ViewModelInject constructor(
    private val sitesApi: SitesApi,
    private val prefsManager: PrefsManager
) : AndroidViewModel() {

    private val siteList = MutableLiveData<List<CountryAvailable>>()

    init {
        if (prefsManager.getString(CountrySelectedPreference()).isBlank()) {
            updateInformation()
        } else {
            launchHome()
        }
    }

    fun updateInformation() {
        disposables.add(sitesApi.getAvailableCountries()
            .doOnSubscribe { showLoading() }
            .doFinally { hideLoading() }
            .subscribe({ siteList.postValue(it.sortedBy { it.name }) }, ::showServiceError)
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

    fun onDataChange(): LiveData<List<CountryAvailable>> = siteList

}