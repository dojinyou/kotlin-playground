package com.dojinyou.orderapitdd.payment

class ConsolePaymentGateway : PaymentGateway {
    override fun execute(cardNumber: String, totalPrice: Int) {
        println("payment ${cardNumber}로 $totalPrice 결제 완료")
    }
}
