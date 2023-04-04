package com.shopping.optimization.productservice.repository

import com.shopping.optimization.productservice.entity.ProductEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : ReactiveCrudRepository<ProductEntity, Long>
