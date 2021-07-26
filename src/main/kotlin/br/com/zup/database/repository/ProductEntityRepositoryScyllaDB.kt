package br.com.zup.database.repository

import br.com.zup.database.entity.ProductEntity
import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.api.core.cql.SimpleStatement
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import javax.inject.Singleton

@Singleton
class ProductEntityRepositoryScyllaDB(private val cqlSession: CqlSession) : ProductEntityRepository {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun save(productEntity: ProductEntity): ProductEntity {
        productEntity.id = UUID.randomUUID()
        cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "INSERT INTO product.Product(id, name, description, category, price, stock) VALUES (?,?,?,?,?,?)",
                    productEntity.id,
                    productEntity.name,
                    productEntity.description,
                    productEntity.category,
                    productEntity.price,
                    productEntity.stock
                )
        )

        logger.info("product registered: {}", productEntity.id)

        return productEntity
    }

    override fun findAll(): List<ProductEntity> {
        val productEntityList: MutableList<ProductEntity> = ArrayList()

        val result = cqlSession.execute(
            SimpleStatement
                .newInstance("SELECT json * FROM product.Product")
        )

        result.forEach { productEntityList.add(ObjectMapper().readValue(it.getString(0), ProductEntity::class.java)) }

        return productEntityList
    }

    override fun findById(id: UUID): Optional<ProductEntity> {
        val query = cqlSession.execute(
            SimpleStatement
                .newInstance("SELECT json * from product.Product WHERE id = ?", id)
        )

        val result = query.one()?.getString(0)

        if (result.isNullOrEmpty()) {
            logger.error("no products found with id: {}", id)
            return Optional.empty()
        }

        return Optional.of(ObjectMapper().readValue(result, ProductEntity::class.java))

    }

    override fun update(productEntity: ProductEntity): ProductEntity {
        cqlSession.execute(
            SimpleStatement
                .newInstance(
                    "UPDATE product.Product SET name = ?, description = ?, category = ?, price = ?, stock = ? WHERE id = ?",
                    productEntity.name,
                    productEntity.description,
                    productEntity.category,
                    productEntity.price,
                    productEntity.stock,
                    productEntity.id
                )
        )

        logger.info("product updated: {}", productEntity.id)

        return productEntity
    }

    override fun delete(productEntity: ProductEntity) {
        cqlSession.execute(
            SimpleStatement
                .newInstance("DELETE from product.Product where ID = ?", productEntity.id)
        )

        logger.info("product deleted: {}", productEntity.id)
    }

}