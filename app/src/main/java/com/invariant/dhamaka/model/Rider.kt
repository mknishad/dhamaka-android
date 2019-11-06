package com.invariant.dhamaka.model

import com.google.gson.annotations.SerializedName
import java.io.File
import java.io.Serializable

data class Rider(
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
) : Serializable
