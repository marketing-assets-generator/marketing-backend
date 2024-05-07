package org.ybigta.marketingbackend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class OriginalImage(
    @Column
    val name: String,
    @Column
    val originalFilename: String?,
    @Column
    val contentType: String?,
    @Column
    val emptyBytes: Boolean,
    @Column
    val size: Long,
    @Column(columnDefinition = "LONGBLOB")
    val bytes: ByteArray,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1,
)
