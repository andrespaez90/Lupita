package com.example.lupita.viewModels

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lupita.data.CountrySelectedPreference
import com.example.lupita.managers.preferences.PrefsManager
import com.example.lupita.network.api.SitesApi
import com.example.lupita.network.models.SeekerProduct
import com.example.lupita.network.models.SitesCategories
import com.example.lupita.viewModels.general.AndroidViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val sitesApi: SitesApi,
    private val prefsManager: PrefsManager
) : AndroidViewModel() {

    private var allCategories: List<SitesCategories> = emptyList()

    private val categories = MutableLiveData<List<SitesCategories>>()

    private val seekerProduct = MutableLiveData<List<SeekerProduct>>()

    private val seekerHandler = Handler(Looper.getMainLooper())

    private val searchTask: Runnable = Runnable { search() }

    var lastQuery = ""
        private set

    /**
     * Categories
     */


    /**
     * Actions
     */

    fun findProduct(query: String) {
        lastQuery = query
        seekerHandler.removeCallbacks(searchTask)
        if (query.isEmpty()) {
            categories.postValue(allCategories)
        } else {seekerHandler.postDelayed(searchTask, 500)
        }
    }

    private fun search() {
        if (lastQuery.isNotEmpty()) {
            disposables.add(
                sitesApi.findProduct(prefsManager.getString(CountrySelectedPreference()), lastQuery)
                    .subscribe({ seekerProduct.postValue(it.results) }, ::showServiceError)
            )
        }
    }


    /**
     * Live Data
     */

    fun onCategoriesChange(): LiveData<List<SitesCategories>> = categories

    fun onSeekerProductChange(): LiveData<List<SeekerProduct>> = seekerProduct
}