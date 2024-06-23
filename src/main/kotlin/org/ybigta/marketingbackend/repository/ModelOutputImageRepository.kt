package org.ybigta.marketingbackend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.ybigta.marketingbackend.entity.ModelOutputImage

@Repository
interface ModelOutputImageRepository : JpaRepository<ModelOutputImage, Long>
