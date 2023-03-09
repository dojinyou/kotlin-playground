package com.dojinyou.orderapitdd.product

import org.springframework.stereotype.Component

@Component
class ProductAdapter(private val productRepository: ProductRepository) : ProductPort {
    override fun save(product: Product) {
        productRepository.save(product)
    }

    override fun getProduct(productId: Long): Product = productRepository.findById(productId).orElseThrow()
}
