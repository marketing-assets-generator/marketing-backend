package org.ybigta.marketingbackend.controller.response

data class ModelOutputImageResponse(
    val id: Long,
    val originalImageId: Long?,
    val prompt: String,
)
