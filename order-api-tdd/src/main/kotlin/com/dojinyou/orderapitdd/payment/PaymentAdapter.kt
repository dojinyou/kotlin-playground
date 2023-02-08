package com.dojinyou.orderapitdd.payment

import com.dojinyou.orderapitdd.order.Order
import com.dojinyou.orderapitdd.order.OrderRepository
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException

@Component
class PaymentAdapter(
    private val orderRepository: OrderRepository,
    private val paymentRepository: PaymentRepository,
    private val paymentGateway: PaymentGateway
): PaymentPort {

    override fun getOrderById(id: Long): Order = orderRepository.findById(id)
        .orElseThrow { IllegalArgumentException("주문이 존재하지 않습니다.") }

    override fun pay(cardNumber: String, totalPrice: Int) {
        paymentGateway.execute(cardNumber, totalPrice)
    }

    override fun save(payment: Payment) {
        paymentRepository.save(payment)
    }
}
