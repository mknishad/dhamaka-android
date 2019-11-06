package com.invariant.dhamaka.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.File

@Parcelize
data class Product(
    var _id: String?,
    var name: String?,
    var phone: String?,
    var gender: String?,
    var dob: String?,
    var imageFile: File?,
    @SerializedName("image")
    var imageUrl: String?,
    var registrationFormNumber: String?,
    var nid: String?,
    var licence: String?
) : Parcelable
