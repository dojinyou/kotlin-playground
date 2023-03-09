package com.dojinyou.orderapitdd.payment

class PaymentStep {

    companion object {
        fun createPaymentRequest(): PaymentRequest {
            val orderId = 1L
            val cardNumber = "1234"
            return PaymentRequest(orderId, cardNumber)
        }
    }

}
