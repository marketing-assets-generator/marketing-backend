package org.ybigta.marketingbackend.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.ybigta.marketingbackend.controller.request.CreateModelOutputImageModificationRequest
import org.ybigta.marketingbackend.controller.response.ModelOutputImageModificationResponse
import org.ybigta.marketingbackend.entity.ModelOutputImageModification
import org.ybigta.marketingbackend.infra.client.ModelClient
import org.ybigta.marketingbackend.infra.client.request.ModelClientModifyImageRequest
import org.ybigta.marketingbackend.repository.ModelOutputImageModificationRepository
import org.ybigta.marketingbackend.repository.ModelOutputImageRepository

@RestController
@RequestMapping("/modelOutputImages/{modelOutputImageId}/modifications")
class ModelOutputImageModificationController(
    private val modelOutputImageRepository: ModelOutputImageRepository,
    private val modelOutputImageModificationRepository: ModelOutputImageModificationRepository,
    private val modelClient: ModelClient,
) {
    @PostMapping
    fun createModelOutputImageModification(
        @PathVariable modelOutputImageId: Long,
        @RequestBody createModelOutputImageModificationRequest: CreateModelOutputImageModificationRequest,
    ): ModelOutputImageModificationResponse {
        val flattenPoints = createModelOutputImageModificationRequest.flattenPoints
        val prompt = createModelOutputImageModificationRequest.prompt

        val modelOutputImage = modelOutputImageRepository.findById(modelOutputImageId).get()
        modelClient.postModelOutputImage(modelOutputImageId, modelOutputImage.bytes)

        val modelClientRequest = ModelClientModifyImageRequest(
            flattenPoints = flattenPoints,
            modelOutputImageId = modelOutputImageId,
            prompt = prompt,
        )
        val bytes = modelClient.modifyImage(modelClientRequest)

        val modelOutputImageModification = ModelOutputImageModification(
            flattenPoints = flattenPoints,
            modelOutputImage = modelOutputImage,
            prompt = prompt,
            bytes = bytes,
        ).run {
            modelOutputImageModificationRepository.save(this)
        }

        return ModelOutputImageModificationResponse(
            id = modelOutputImageModification.id,
            modelOutputImageId = modelOutputImageModification.modelOutputImage.id,
            flattenPoints = modelOutputImageModification.flattenPoints,
        )
    }

    @GetMapping("/{modelOutputImageModificationId}")
    fun getModelOutputImageModification(
        @PathVariable modelOutputImageModificationId: Long,
    ): ModelOutputImageModificationResponse {
        val modelOutputImageModification =
            modelOutputImageModificationRepository.findById(modelOutputImageModificationId).get()
        return ModelOutputImageModificationResponse(
            id = modelOutputImageModification.id,
            modelOutputImageId = modelOutputImageModification.modelOutputImage.id,
            flattenPoints = modelOutputImageModification.flattenPoints,
        )
    }

    @GetMapping(
        "/{modelOutputImageModificationId}/bytes",
        produces = ["image/jpeg", "image/png", "image/gif"],
    )
    fun getModelOutputImageModificationBytes(@PathVariable modelOutputImageModificationId: Long): ByteArray {
        val modelOutputImageModification =
            modelOutputImageModificationRepository.findById(modelOutputImageModificationId).get()
        val bytes = modelOutputImageModification.bytes
        return bytes
    }
}
