package org.ybigta.marketingbackend.infra.client.request

data class ModelClientModifyImageRequest(
    val flattenPoints: List<Int>,
    val modelOutputImageId: Long,
    val prompt: String,
)
