package com.example.lupita.network.models

import com.google.gson.annotations.SerializedName

data class Sites(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("default_currency_id") val currency: String
)

data class SitesCategories(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)