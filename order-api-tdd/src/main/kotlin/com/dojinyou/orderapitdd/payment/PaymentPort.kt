package com.dojinyou.orderapitdd.payment

import com.dojinyou.orderapitdd.order.Order


interface PaymentPort {
    fun getOrderById(id:Long): Order
    fun pay(cardNumber: String, totalPrice: Int)
    fun save(payment: Payment)
}
