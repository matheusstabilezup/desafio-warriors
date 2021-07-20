package br.com.zup.database.service

import br.com.zup.core.mapper.ProductConverter
import br.com.zup.core.model.Product
import br.com.zup.core.port.ProductRepositoryPort
import br.com.zup.database.entity.ProductEntity
import br.com.zup.database.repository.ProductRepository
import javax.inject.Singleton

@Singleton
class ProductEntityService(private val productRepository: ProductRepository) : ProductRepositoryPort {
    override fun save(productEntity: ProductEntity) = ProductConverter.toProduct(productRepository.save(productEntity))

    override fun findAll() = ProductConverter.toListProduct(productRepository.findAll())

    override fun findById(id: Long) = ProductConverter.toProduct(productRepository.findById(id).orElseThrow())

    override fun update(productEntity: ProductEntity): Product {
        val validate = productRepository.findById(productEntity.id!!).orElseThrow()
        return ProductConverter.toProduct(productRepository.update(productEntity))
    }

    override fun delete(id: Long) {
        val productEntity = productRepository.findById(id).orElseThrow()
        productRepository.delete(productEntity)
    }
}