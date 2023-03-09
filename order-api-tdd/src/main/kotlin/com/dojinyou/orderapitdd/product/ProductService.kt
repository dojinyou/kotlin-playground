package com.dojinyou.orderapitdd.product

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductService(private val productPort: ProductPort) {

    @PostMapping
    @Transactional
    fun addProduct(@RequestBody request: AddProductRequest): ResponseEntity<Unit> {
        val product = Product(name = request.name, price = request.price, discountPolicy = request.discountPolicy)
        productPort.save(product)

        return ResponseEntity.status(HttpStatus.CREATED.value()).build()
    }

    @GetMapping("/{productId}")
    @Transactional(readOnly = true)
    fun getProduct(@PathVariable productId: Long): ResponseEntity<GetProductResponse> {
        val product: Product = productPort.getProduct(productId)

        val response = GetProductResponse(
            id = product.id!!,
            name = product.name!!,
            price = product.price!!,
            discountPolicy = product.discountPolicy!!,
        )

        return ResponseEntity.ok(response)
    }

    @PutMapping("/{productId}")
    @Transactional
    fun updateProduct(@PathVariable productId: Long, @RequestBody request: UpdateProductRequest): ResponseEntity<Unit> {
        val product = productPort.getProduct(productId)
        product.update(
            name = request.name,
            price = request.price,
            discountPolicy = request.discountPolicy
        )
        productPort.save(product)

        return ResponseEntity.status(HttpStatus.OK).build()
    }
}
