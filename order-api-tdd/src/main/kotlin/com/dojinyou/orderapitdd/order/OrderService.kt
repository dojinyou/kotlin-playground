package com.dojinyou.orderapitdd.order

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orders")
class OrderService(
    private val orderPort: OrderPort
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(@RequestBody request: CreateOrderRequest) {
        val product = orderPort.getProductById(request.productId)

        val order = Order(product = product, quantity = request.quantity)

        orderPort.save(order)
    }
}
