package br.com.zup.database.service

import br.com.zup.core.mapper.ProductConverter
import br.com.zup.core.model.Product
import br.com.zup.core.port.ProductRepositoryPort
import br.com.zup.database.entity.ProductEntity
import br.com.zup.database.repository.ProductEntityRepository
import java.util.*
import javax.inject.Singleton

@Singleton
class ProductEntityService(private val productEntityRepository: ProductEntityRepository) : ProductRepositoryPort {
    override fun save(productEntity: ProductEntity) =
        ProductConverter.toProduct(productEntityRepository.save(productEntity))

    override fun findAll() = ProductConverter.toListProduct(productEntityRepository.findAll())

    override fun findById(id: UUID) = ProductConverter.toProduct(productEntityRepository.findById(id).orElseThrow())

    override fun update(productEntity: ProductEntity): Product {
        val validate = productEntityRepository.findById(productEntity.id!!).orElseThrow()
        return ProductConverter.toProduct(productEntityRepository.update(productEntity))
    }

    override fun delete(id: UUID) {
        val productEntity = productEntityRepository.findById(id).orElseThrow()
        productEntityRepository.delete(productEntity)
    }


}