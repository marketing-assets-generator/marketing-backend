package org.ybigta.marketingbackend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class ModelOutputImage(
    @Column(length = 500)
    val prompt: String,

    @Column(columnDefinition = "LONGBLOB")
    val bytes: ByteArray,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1,
)
