package br.com.zup.database.repository

import br.com.zup.database.entity.ProductEntity
import java.util.*
import javax.inject.Singleton

interface ProductEntityRepository {
    fun save(productEntity: ProductEntity): ProductEntity
    fun findAll(): List<ProductEntity>
    fun findById(id: UUID): Optional<ProductEntity>
    fun update(productEntity: ProductEntity): ProductEntity
    fun delete(productEntity: ProductEntity)
}