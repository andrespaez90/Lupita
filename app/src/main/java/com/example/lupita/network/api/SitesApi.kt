package com.example.lupita.network.api

import com.example.lupita.network.models.SeekerResponse
import com.example.lupita.network.models.Sites
import com.example.lupita.network.services.SitesServices
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.annotations.CheckReturnValue
import javax.inject.Inject

class SitesApi @Inject constructor(private val services: SitesServices) : BaseApi() {

    @CheckReturnValue
    fun getSites(scheduler: Scheduler? = null): Single<List<Sites>> =
        subscribe(services.getSites(), scheduler)

    @CheckReturnValue
    fun findProduct(
        siteId: String,
        query: String,
        scheduler: Scheduler? = null
    ): Single<SeekerResponse> =
        subscribe(services.searchProduct(siteId, query), scheduler)
}