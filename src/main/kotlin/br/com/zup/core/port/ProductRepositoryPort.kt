package br.com.zup.core.port

import br.com.zup.core.model.Product
import br.com.zup.database.entity.ProductEntity

interface ProductRepositoryPort {
    fun save(productEntity: ProductEntity): Product
    fun findAll(): List<Product>
    fun findById(id: Long): Product
    fun update(productEntity: ProductEntity): Product
    fun delete(id: Long)
}