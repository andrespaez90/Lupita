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

class SelectSiteVewModel @Inject constructor() : AndroidViewModel() {
}