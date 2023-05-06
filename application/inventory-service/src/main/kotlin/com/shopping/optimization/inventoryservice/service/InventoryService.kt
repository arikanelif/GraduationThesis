package com.shopping.optimization.inventoryservice.service

import com.shopping.optimization.inventoryservice.entity.InventoryEntity
import com.shopping.optimization.inventoryservice.model.InventoryResponseModel
import com.shopping.optimization.inventoryservice.repository.InventoryRepository
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.kotlin.core.publisher.toFlux


@Service
class InventoryService(
    private val inventoryRepository: InventoryRepository
) {
    @Transactional(readOnly = true)
    fun isInStock(skuCode: List<String>): List<InventoryResponseModel> {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
            .map { inventory -> InventoryResponseModel(inventory.skuCode, inventory.isInStock) }.toList()
    }

    suspend fun getAllInventory(): List<InventoryEntity>? {
        return runBlocking{ inventoryRepository.findAll().collectList().awaitSingleOrNull() }
    }
}