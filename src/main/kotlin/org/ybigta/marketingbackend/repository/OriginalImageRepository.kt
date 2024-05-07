package org.ybigta.marketingbackend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.ybigta.marketingbackend.entity.OriginalImage

interface OriginalImageRepository : JpaRepository<OriginalImage, Long>
