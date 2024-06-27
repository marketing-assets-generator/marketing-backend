package org.ybigta.marketingbackend.infra.client.request

data class ModelClientGenerateImageRequest(
    val prompt: String,
    val originalImageId: Long?,
)
