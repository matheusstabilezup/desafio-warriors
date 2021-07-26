package br.com.zup.core.model

import java.math.BigDecimal
import java.util.*

data class Product(
    val id: UUID?,
    val name: String,
    val description: String,
    val category: String,
    val price: BigDecimal,
    val stock: Int
)