package com.dojinyou.orderapitdd.order

import com.dojinyou.orderapitdd.product.Product
import com.dojinyou.orderapitdd.product.ProductRepository
import org.springframework.stereotype.Component

@Component
class OrderAdapter(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository
): OrderPort {
    override fun getProductById(productId: Long): Product {
        return productRepository.findById(productId).orElseThrow()
    }

    override fun save(order: Order) {
        orderRepository.save(order)
    }
}
