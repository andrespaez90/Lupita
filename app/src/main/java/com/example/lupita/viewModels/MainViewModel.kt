package com.example.lupita.viewModels

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.CheckResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lupita.data.CountrySelectedPreference
import com.example.lupita.managers.preferences.PrefsManager
import com.example.lupita.network.api.SitesApi
import com.example.lupita.network.models.SeekerProduct
import com.example.lupita.network.models.SitesCategories
import com.example.lupita.ui.activities.DetailProductActivity
import com.example.lupita.ui.activities.SitesActivity
import com.example.lupita.viewModels.general.AndroidViewModel
import com.example.lupita.viewModels.models.FinishActivityModel
import com.example.lupita.viewModels.models.StartActivityModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Collections.emptyList
import javax.inject.Inject

@HiltViewModel
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

    init {
        getCategories()
    }

    /**
     * Categories
     */

    fun getCategories() {
        disposables.add(sitesApi.getCategories(prefsManager.getString(CountrySelectedPreference()))
            .doOnSubscribe { showLoading() }
            .doFinally { hideLoading() }
            .subscribe(
                { allCategories = it; categories.postValue(allCategories) },
                ::showServiceError
            )
        )
    }

    /**
     * Actions
     */

    fun findProduct(query: String, isWriting: Boolean = true) {
        lastQuery = query
        seekerHandler.removeCallbacks(searchTask)
        when {
            query.isEmpty() -> categories.postValue(allCategories)
            isWriting -> seekerHandler.postDelayed(searchTask, 500)
            else -> search()
        }
    }

    private fun search() {
        if (lastQuery.isNotEmpty()) {
            disposables.add(
                sitesApi.findProduct(prefsManager.getString(CountrySelectedPreference()), lastQuery)
                    .doOnSubscribe { showLoading() }
                    .doOnSuccess { hideLoading() }
                    .subscribe({ seekerProduct.postValue(it.results) }, ::showServiceError)
            )
        }
    }

    /**
     * Functions
     */

    fun changeCountry() {
        prefsManager.set(CountrySelectedPreference(), "")
        startActivity.postValue(StartActivityModel(SitesActivity::class.java))
        closeView.postValue(FinishActivityModel(Activity.RESULT_OK))
    }

    fun onProductClicked(product: SeekerProduct) {
        startActivity.postValue(
            StartActivityModel(
                DetailProductActivity::class.java,
                Bundle().apply { putParcelable("product", product) }
            )
        )
    }

    /**
     * Live Data
     */

    @CheckResult
    fun onCategoriesChange(): LiveData<List<SitesCategories>> = categories

    @CheckResult
    fun onSeekerProductChange(): LiveData<List<SeekerProduct>> = seekerProduct
}