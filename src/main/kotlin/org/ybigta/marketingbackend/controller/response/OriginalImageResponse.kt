package org.ybigta.marketingbackend.controller.response

data class OriginalImageResponse(
    val id: Long,
    val name: String,
    val originalFilename: String?,
    val contentType: String?,
    val emptyBytes: Boolean,
    val size: Long,
)
