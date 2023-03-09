package com.dojinyou.orderapitdd.payment

interface PaymentGateway {

    fun execute(cardNumber: String, totalPrice: Int)

}
