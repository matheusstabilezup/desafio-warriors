package br.com.zup.core.model

import java.math.BigDecimal

data class Product(
    val id: Long?,
    val name: String,
    val description: String,
    val category: String,
    val price: BigDecimal,
    val stock: Int
)