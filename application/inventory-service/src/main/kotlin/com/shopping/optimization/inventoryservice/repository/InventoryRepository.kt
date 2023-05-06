package com.shopping.optimization.inventoryservice.repository

import com.shopping.optimization.inventoryservice.entity.InventoryEntity
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface InventoryRepository: ReactiveCrudRepository<InventoryEntity, Long> {

    fun findBySkuCodeIn(skuCode: List<String>): List<InventoryEntity>

    @Query("SELECT * FROM inventory")
    override fun findAll(): Flux<InventoryEntity>
}