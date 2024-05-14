package org.ybigta.marketingbackend.entity.base

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AuditLoggingBase {
    @Column(nullable = false)
    @CreatedDate
    lateinit var createdAt: Instant

    @Column(nullable = false)
    @LastModifiedDate
    lateinit var updatedAt: Instant
}
