package br.com.zup.database.repository

import br.com.zup.database.entity.ProductEntity
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface ProductRepository : JpaRepository<ProductEntity, Long> {
}