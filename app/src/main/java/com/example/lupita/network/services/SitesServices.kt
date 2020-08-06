package com.example.lupita.network.services

import com.example.lupita.network.models.CountryModel
import com.example.lupita.network.models.SeekerResponse
import com.example.lupita.network.models.Sites
import com.example.lupita.network.models.SitesCategories
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SitesServices {

    @GET("sites")
    fun getSites(): Single<List<Sites>>

    @GET("classified_locations/countries")
    fun getCountries(): Single<List<CountryModel>>

    @GET("sites/{site_id}/categories")
    fun getCategoriesBySite(@Path("site_id") siteId: String): Single<List<SitesCategories>>

    @GET("sites/{site_id}/search")
    fun searchProduct(@Path("site_id") siteId: String, @Query("q") query: String): Single<SeekerResponse>

}