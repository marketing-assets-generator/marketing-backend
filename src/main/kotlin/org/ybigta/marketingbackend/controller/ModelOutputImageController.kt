package org.ybigta.marketingbackend.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.ybigta.marketingbackend.controller.request.CreateModelOutputImageRequest
import org.ybigta.marketingbackend.controller.response.CreateModelOutputImageResponse
import org.ybigta.marketingbackend.entity.ModelOutputImage
import org.ybigta.marketingbackend.infra.client.ModelClient
import org.ybigta.marketingbackend.infra.client.request.ModelClientGetImageRequest
import org.ybigta.marketingbackend.repository.ModelOutputImageRepository

@RestController
@RequestMapping("/modelOutputImages")
class ModelOutputImageController(
    private val modelOutputImageRepository: ModelOutputImageRepository,
    private val modelClient: ModelClient,
) {

    @PostMapping("")
    fun createModelOutputImage(
        @RequestBody createModelOutputImageRequest: CreateModelOutputImageRequest,
    ): CreateModelOutputImageResponse {
        val request = ModelClientGetImageRequest(prompt = createModelOutputImageRequest.prompt)
        val bytes = modelClient.getImage(request)

        val modelOutputImage = ModelOutputImage(
            prompt = createModelOutputImageRequest.prompt,
            bytes = bytes,
        )
        modelOutputImageRepository.save(modelOutputImage)

        return CreateModelOutputImageResponse(
            id = modelOutputImage.id,
            prompt = modelOutputImage.prompt,
        )
    }

    @GetMapping(
        "/{modelOutputImageId}/bytes",
        produces = ["image/jpeg", "image/png", "image/gif"],
    )
    fun getModelOutputImageBytes(@PathVariable modelOutputImageId: Long): ByteArray {
        val modelOutputImage = modelOutputImageRepository.findById(modelOutputImageId).get()
        val bytes = modelOutputImage.bytes
        return bytes
    }
}
