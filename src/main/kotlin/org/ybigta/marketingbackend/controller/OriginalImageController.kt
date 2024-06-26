package org.ybigta.marketingbackend.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.ybigta.marketingbackend.controller.response.OriginalImageResponse
import org.ybigta.marketingbackend.entity.OriginalImage
import org.ybigta.marketingbackend.repository.OriginalImageRepository

@RestController
@RequestMapping("/originalImages")
class OriginalImageController(
    private val originalImageRepository: OriginalImageRepository,
) {
    @PostMapping
    fun createOriginalImage(@RequestPart("file") file: MultipartFile): OriginalImageResponse {
        val originalImage = OriginalImage(
            name = file.name,
            originalFilename = file.originalFilename,
            contentType = file.contentType,
            emptyBytes = file.isEmpty,
            size = file.size,
            bytes = file.bytes,
        ).run {
            originalImageRepository.save(this)
        }

        return OriginalImageResponse(
            id = originalImage.id,
            name = originalImage.name,
            originalFilename = originalImage.originalFilename,
            contentType = originalImage.contentType,
            emptyBytes = originalImage.emptyBytes,
            size = originalImage.size,
        )
    }

    @GetMapping(
        "/{originalImageId}/bytes",
        produces = ["image/jpeg", "image/png", "image/gif"],
    )
    fun getOriginalImageBytes(@PathVariable originalImageId: Long): ByteArray {
        val originalImage = originalImageRepository.findById(originalImageId).get()
        val bytes = originalImage.bytes
        return bytes
    }
}
