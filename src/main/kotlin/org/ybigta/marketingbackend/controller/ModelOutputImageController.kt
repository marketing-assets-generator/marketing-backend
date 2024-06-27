package org.ybigta.marketingbackend.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.ybigta.marketingbackend.controller.request.CreateModelOutputImageRequest
import org.ybigta.marketingbackend.controller.response.ModelOutputImageResponse
import org.ybigta.marketingbackend.entity.ModelOutputImage
import org.ybigta.marketingbackend.entity.OriginalImage
import org.ybigta.marketingbackend.infra.client.ModelClient
import org.ybigta.marketingbackend.infra.client.request.ModelClientGenerateImageRequest
import org.ybigta.marketingbackend.repository.ModelOutputImageRepository
import org.ybigta.marketingbackend.repository.OriginalImageRepository

@RestController
@RequestMapping("/modelOutputImages")
class ModelOutputImageController(
    private val originalImageRepository: OriginalImageRepository,
    private val modelOutputImageRepository: ModelOutputImageRepository,
    private val modelClient: ModelClient,
) {

    @PostMapping
    fun createModelOutputImage(
        @RequestBody createModelOutputImageRequest: CreateModelOutputImageRequest,
    ): ModelOutputImageResponse {
        val originalImageId = createModelOutputImageRequest.originalImageId

        val originalImage: OriginalImage?
        if (originalImageId != null) {
            originalImage = originalImageRepository.findById(originalImageId).get()
            modelClient.postOriginalImage(originalImageId, originalImage.bytes)
        } else originalImage = null

        val request = ModelClientGenerateImageRequest(
            prompt = createModelOutputImageRequest.prompt,
            originalImageId = originalImageId
        )
        val bytes = modelClient.generateImage(request)

        val modelOutputImage = ModelOutputImage(
            prompt = createModelOutputImageRequest.prompt,
            originalImage = originalImage,
            bytes = bytes,
        ).run { modelOutputImageRepository.save(this) }

        return ModelOutputImageResponse(
            id = modelOutputImage.id,
            originalImageId = modelOutputImage.originalImage?.id,
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
