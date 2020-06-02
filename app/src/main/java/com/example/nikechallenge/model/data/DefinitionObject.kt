package com.example.nikechallenge.model.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

// Each definition given by the RapidAPI response
@Parcelize
data class DefinitionObject(
    @field:Json(name = "definition") var definition: String,
    @field:Json(name = "example") var example: String,
    @field:Json(name = "thumbs_up") var thumbs_up: Int,
    @field:Json(name = "thumbs_down") var thumbs_down: Int,
    @field:Json(name = "author") var author: String) : Parcelable

// The response given by RapidAPI
data class DefinitionResponse(
    @field:Json(name = "list") var list: List<DefinitionObject>)