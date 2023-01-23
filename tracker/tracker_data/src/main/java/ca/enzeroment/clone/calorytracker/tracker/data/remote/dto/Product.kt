package ca.enzeroment.clone.calorytracker.tracker.data.remote.dto

import com.squareup.moshi.Json

data class Product(
    @field:Json(name = "image_front_thumb_url")
    val imageFrontThumbUrl : String?,
    val nutrients : Nutrients,
    @field:Json(name = "product_name")
    val productName : String?
)
