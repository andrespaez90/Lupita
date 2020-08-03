package com.example.lupita.network.models

import com.google.gson.annotations.SerializedName

data class SeekerResponse (
    @SerializedName("results") val results : List<SeekerProduct>
)

data class SeekerProduct(
    @SerializedName("id") val id: String,
    @SerializedName("title") val name: String,
    @SerializedName("price") val price: String,
    @SerializedName("available_quantity") val availableQuantity: Int,
    @SerializedName("thumbnail") val image: String,
    @SerializedName("condition") val condition: String,
    @SerializedName("seller") val seller: Seller
)

data class Seller(
    @SerializedName("id") val id: String,
    @SerializedName("power_seller_status") val status: String
)