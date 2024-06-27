package org.ybigta.marketingbackend.entity

import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import org.ybigta.marketingbackend.entity.base.AuditLoggingBase

@Entity
class ModelOutputImageModification(
    @Column
    @ElementCollection
    val flattenPoints: List<Int>,

    @ManyToOne
    val modelOutputImage: ModelOutputImage,

    @Column
    val prompt: String,

    @Column(columnDefinition = "LONGBLOB", nullable = false)
    val bytes: ByteArray,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1,
) : AuditLoggingBase()
