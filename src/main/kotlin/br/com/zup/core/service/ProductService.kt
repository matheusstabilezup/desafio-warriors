package br.com.zup.core.service

import br.com.zup.core.mapper.ProductConverter
import br.com.zup.core.model.Product
import br.com.zup.core.port.ProductRepositoryPort
import br.com.zup.core.port.ProductServicePort
import java.util.*
import javax.inject.Singleton

@Singleton
class ProductService(private val productRepository: ProductRepositoryPort) : ProductServicePort {

    override fun create(product: Product) = productRepository.save(ProductConverter.toEntity(product))

    override fun findAll() = productRepository.findAll()

    override fun findById(id: UUID) = productRepository.findById(id)

    override fun update(product: Product) = productRepository.update(ProductConverter.toEntity(product))

    override fun delete(id: UUID) = productRepository.delete(id)
}