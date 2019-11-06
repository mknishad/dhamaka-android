package com.invariant.dhamaka.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    var _id: String?,
    var name: String?,
    var rating: Float?,
    var price: Double?,
    var salePrice: Double?,
    var imageUrl: String?
) : Parcelable
