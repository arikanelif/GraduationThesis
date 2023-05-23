package com.shopping.optimization.inventoryservice.service

import com.shopping.optimization.inventoryservice.entity.InventoryEntity
import com.shopping.optimization.inventoryservice.model.InventoryResponseModel
import com.shopping.optimization.inventoryservice.repository.InventoryRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class InventoryService(
    private val inventoryRepository: InventoryRepository
) {
    @Transactional(readOnly = true)
    suspend fun isInStock(skuCode: List<String>): List<InventoryResponseModel> {
        return inventoryRepository.findBySkuCodeIn(skuCode)
            .map { InventoryResponseModel(it.skuCode, it.isInStock) }
            .collectList()
            .awaitSingle()
    }

    @Transactional(readOnly = true)
    suspend fun getAllInventory(): List<InventoryEntity> {
        return inventoryRepository.findAll().collectList().awaitSingle()
    }
}