package com.example.lupita.network.services

import com.example.lupita.network.models.Sites
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface SitesServices {

    @GET("sites")
    fun getSites(): Single<List<Sites>>

    @GET("sites/{site_id}/categories.")
    fun getCategoriesBySite(@Path("site_id") siteId: String): Completable

}