package com.example.lupita.network.api

import com.example.lupita.network.models.CountryAvailable
import com.example.lupita.network.models.SeekerResponse
import com.example.lupita.network.models.Sites
import com.example.lupita.network.models.SitesCategories
import com.example.lupita.network.services.SitesServices
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class SitesApi @Inject constructor(private val services: SitesServices) : BaseApi() {

    @CheckReturnValue
    fun getSites(scheduler: Scheduler? = null): Single<List<Sites>> =
        subscribe(services.getSites(), scheduler)

    @CheckReturnValue
    fun getCategories(siteId: String, scheduler: Scheduler? = null): Single<List<SitesCategories>> =
        subscribe(services.getCategoriesBySite(siteId), scheduler)

    @CheckReturnValue
    fun findProduct(
        siteId: String,
        query: String,
        scheduler: Scheduler? = null
    ): Single<SeekerResponse> =
        subscribe(services.searchProduct(siteId, query), scheduler)

    @CheckReturnValue
    fun getAvailableCountries(scheduler: Scheduler? = null): Single<List<CountryAvailable>> =
        subscribe(services.getSites()
            .zipWith(services.getCountries(),
                BiFunction { sites, countries ->
                    sites.map { site ->
                        val country = countries.firstOrNull { it.name == site.name }
                        CountryAvailable(site.id, site.name, country?.id ?: "")
                    }
                }
            ), scheduler)
}