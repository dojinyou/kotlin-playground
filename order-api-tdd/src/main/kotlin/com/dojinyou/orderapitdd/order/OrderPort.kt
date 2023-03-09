package com.dojinyou.orderapitdd.order

import com.dojinyou.orderapitdd.product.Product

interface OrderPort {

    fun getProductById(productId: Long): Product
    fun save(order: Order)

}
