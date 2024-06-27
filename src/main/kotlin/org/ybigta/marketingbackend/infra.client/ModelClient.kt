package org.ybigta.marketingbackend.infra.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.ybigta.marketingbackend.infra.client.request.ModelClientGenerateImageRequest
import org.ybigta.marketingbackend.infra.client.request.ModelClientModifyImageRequest

@FeignClient(name = "model-server", url = "\${model-server.url}")
interface ModelClient {
    @PostMapping("/originalImages/{originalImageId}", consumes = ["image/jpeg", "image/png", "image/gif"])
    fun postOriginalImage(
        @PathVariable originalImageId: Long,
        @RequestBody bytes: ByteArray,
    )

    @PostMapping("/modelOutputImages/{modelOutputImageId}", consumes = ["image/jpeg", "image/png", "image/gif"])
    fun postModelOutputImage(
        @PathVariable modelOutputImageId: Long,
        @RequestBody bytes: ByteArray,
    )

    @PostMapping("/generate", produces = ["image/jpeg", "image/png", "image/gif"])
    fun generateImage(
        @RequestBody modelClientGenerateImageRequest: ModelClientGenerateImageRequest,
    ): ByteArray

    @PostMapping("/modify", produces = ["image/jpeg", "image/png", "image/gif"])
    fun modifyImage(
        @RequestBody modelClientModifyImageRequest: ModelClientModifyImageRequest,
    ): ByteArray
}
