package com.shopping.optimization.inventoryservice.controller

import com.shopping.optimization.inventoryservice.entity.InventoryEntity
import com.shopping.optimization.inventoryservice.model.InventoryResponseModel
import com.shopping.optimization.inventoryservice.service.InventoryService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api/inventory")
class InventoryController(
    private val inventoryService: InventoryService,

) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    suspend fun isInStock(
        @RequestParam skuCode: List<String>
    ): List<InventoryResponseModel> {
        return inventoryService.isInStock(skuCode)
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    suspend fun getAllInventory(): List<InventoryEntity>? {
        return inventoryService.getAllInventory()
    }
    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    suspend fun test(
    ): List<InventoryResponseModel> {
        return inventoryService.isInStock(listOf("iphone13", "iphone12"))
    }




}