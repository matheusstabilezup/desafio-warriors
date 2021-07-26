package br.com.zup.database.entity

import java.math.BigDecimal
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: UUID? = null,
    val name: String = "",
    val description: String = "",
    val category: String = "",
    val price: BigDecimal = BigDecimal.ONE,
    val stock: Int = 0
) {
    override fun toString(): String {
        return "ProductEntity(id=$id, name='$name', description='$description', category='$category', price=$price, stock=$stock)"
    }
}