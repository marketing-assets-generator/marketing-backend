package org.ybigta.marketingbackend.controller.request

data class CreateModelOutputImageRequest(
    val originalImageId: Long?,
    val prompt: String,
)
