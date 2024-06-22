package org.ybigta.marketingbackend.infra.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.ybigta.marketingbackend.infra.client.request.ModelClientGetImageRequest

@FeignClient(name = "model-server", url = "\${model-server.url}")
interface ModelClient {
    @PostMapping("/image", produces = ["image/jpeg", "image/png", "image/gif"])
    fun getImage(
        @RequestBody modelClientGetImageRequest: ModelClientGetImageRequest,
    ): ByteArray
}
