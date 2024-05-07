package org.ybigta.marketingbackend.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.ybigta.marketingbackend.controller.response.OriginalImageResponse
import org.ybigta.marketingbackend.entity.OriginalImage
import org.ybigta.marketingbackend.repository.OriginalImageRepository

@RestController
class FileTestController(
    private val originalImageRepository: OriginalImageRepository,
) {
    @PostMapping("/")
    fun uploadFile(@RequestPart("file") file: MultipartFile): OriginalImageResponse {
        val originalImage = OriginalImage(
            name = file.name,
            originalFilename = file.originalFilename,
            contentType = file.contentType,
            emptyBytes = file.isEmpty,
            size = file.size,
            bytes = file.bytes,
        )

        originalImageRepository.save(originalImage)

        return OriginalImageResponse(
            id = originalImage.id,
            name = originalImage.name,
            originalFilename = originalImage.originalFilename,
            contentType = originalImage.contentType,
            emptyBytes = originalImage.emptyBytes,
            size = originalImage.size,
        )
    }
}
