package org.ybigta.marketingbackend.controller.request

data class CreateModelOutputImageModificationRequest(
    val flattenPoints: List<Int>,
    val prompt: String,
)
