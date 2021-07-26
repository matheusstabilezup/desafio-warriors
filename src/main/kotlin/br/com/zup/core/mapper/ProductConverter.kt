package br.com.zup.core.mapper

import br.com.zup.core.model.Product
import br.com.zup.database.entity.ProductEntity
import br.com.zup.entrypoint.dto.ProductDto
import java.util.*

class ProductConverter {
    companion object {
        fun toProduct(dto: ProductDto) =
            Product(null, dto.name, dto.description, dto.category, dto.price, dto.stock)

        fun toProduct(productEntity: ProductEntity) = Product(
            productEntity.id,
            productEntity.name,
            productEntity.description,
            productEntity.category,
            productEntity.price,
            productEntity.stock
        )

        fun toProductWithId(id: UUID, dto: ProductDto) =
            Product(id, dto.name, dto.description, dto.category, dto.price, dto.stock)

        fun toDto(product: Product) =
            ProductDto(product.name, product.description, product.category, product.price, product.stock)

        fun toListDto(productList: List<Product>) = productList.map { toDto(it) }

        fun toListProduct(productEntityList: List<ProductEntity>) = productEntityList.map { toProduct(it) }

        fun toEntity(product: Product) =
            ProductEntity(product.id, product.name, product.description, product.category, product.price, product.stock)
    }
}