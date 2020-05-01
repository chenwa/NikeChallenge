package com.example.nikechallenge.model

import com.squareup.moshi.Json

// Each definition given by the RapidAPI response
data class DefinitionObject(
    @field:Json(name = "definition") var definition: String,
    @field:Json(name = "example") var example: String,
    @field:Json(name = "thumbs_up") var thumbs_up: Int,
    @field:Json(name = "thumbs_down") var thumbs_down: Int,
    @field:Json(name = "author") var author: String)

// The response given by RapidAPI
data class DefinitionResponse(
    @field:Json(name = "list") var list: List<DefinitionObject>)