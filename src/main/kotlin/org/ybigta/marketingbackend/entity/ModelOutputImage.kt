package org.ybigta.marketingbackend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class ModelOutputImage(
    @Column(length = 500, nullable = false)
    val prompt: String,

    @Column(columnDefinition = "LONGBLOB", nullable = false)
    val bytes: ByteArray,

    @ManyToOne
    val originalImage: OriginalImage?,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1,
)
