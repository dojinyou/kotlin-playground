package com.dojinyou.orderapitdd.payment

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/payments")
class PaymentService(
    private val paymentPort: PaymentPort
) {
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun payment(@RequestBody request: PaymentRequest) {
        val order = paymentPort.getOrderById(request.orderId)
        val payment = Payment(cardNumber = request.cardNumber, order = order)

        paymentPort.pay(cardNumber = payment.cardNumber!!, totalPrice = order.getTotalPrice())
        paymentPort.save(payment)
    }
}
