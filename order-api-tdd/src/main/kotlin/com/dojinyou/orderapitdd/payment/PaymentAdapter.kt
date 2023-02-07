package com.dojinyou.orderapitdd.payment

import com.dojinyou.orderapitdd.order.OrderRepository

class PaymentAdapter(
    private val orderRepository: OrderRepository,
    private val paymentRepository: PaymentRepository,
    private val paymentGateway: PaymentGateway
): PaymentPort {

    override fun getOrderById(id: Long) = orderRepository.findById(id).get()

    override fun pay(cardNumber: String, totalPrice: Int) {
        paymentGateway.execute(cardNumber, totalPrice)
    }

    override fun save(payment: Payment) {
        paymentRepository.save(payment)
    }
}
