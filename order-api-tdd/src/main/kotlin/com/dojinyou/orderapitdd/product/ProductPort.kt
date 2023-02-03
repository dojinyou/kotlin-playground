package com.dojinyou.orderapitdd.product

interface ProductPort {
    fun save(product: Product)

    fun getProduct(productId: Long): Product
}
