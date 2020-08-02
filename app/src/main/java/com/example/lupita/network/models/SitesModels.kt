package com.example.lupita.network.models

import com.google.gson.annotations.SerializedName

class Sites(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("default_currency_id") val currency: String
)

class SitesCategories(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)