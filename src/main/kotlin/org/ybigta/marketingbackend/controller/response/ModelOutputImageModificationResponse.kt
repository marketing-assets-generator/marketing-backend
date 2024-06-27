package org.ybigta.marketingbackend.controller.response

data class ModelOutputImageModificationResponse(
    val id: Long,
    val modelOutputImageId: Long,
    val flattenPoints: List<Int>,
)
