package org.ybigta.marketingbackend.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.ybigta.marketingbackend.entity.base.AuditLoggingBase

@Entity
class OriginalImage(
    @Column(nullable = false)
    val name: String,

    @Column
    val originalFilename: String?,

    @Column
    val contentType: String?,

    @Column(nullable = false)
    val emptyBytes: Boolean,

    @Column(nullable = false)
    val size: Long,

    @Column(columnDefinition = "LONGBLOB")
    val bytes: ByteArray,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1,
) : AuditLoggingBase()
