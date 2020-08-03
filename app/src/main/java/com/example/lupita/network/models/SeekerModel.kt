package com.example.lupita.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class SeekerResponse (
    @SerializedName("results") val results : List<SeekerProduct>
)

@Parcelize
data class SeekerProduct(
    @SerializedName("id") val id: String,
    @SerializedName("title") val name: String,
    @SerializedName("price") val price: String,
    @SerializedName("available_quantity") val availableQuantity: Int,
    @SerializedName("thumbnail") val image: String,
    @SerializedName("condition") val condition: String,
    @SerializedName("permalink") val link: String,
    @SerializedName("seller") val seller: Seller
): Parcelable

@Parcelize
data class Seller(
    @SerializedName("id") val id: String,
    @SerializedName("power_seller_status") val status: String?
): Parcelable