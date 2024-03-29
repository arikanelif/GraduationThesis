package com.shopping.optimization.inventoryservice.repository

import com.shopping.optimization.inventoryservice.entity.InventoryEntity
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface InventoryRepository: ReactiveCrudRepository<InventoryEntity, Long> {

    @Query("SELECT * FROM inventory WHERE sku_code IN (:skuCode)")
    fun findBySkuCodeIn(skuCode: List<String>): Flux<InventoryEntity>

    @Query("SELECT * FROM inventory")
    override fun findAll(): Flux<InventoryEntity>
}