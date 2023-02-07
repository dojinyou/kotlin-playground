package com.dojinyou.orderapitdd.payment

class PaymentService(
    private val paymentPort: PaymentPort
) {
    fun payment(request: PaymentRequest) {
        val order = paymentPort.getOrderById(request.orderId)
        val payment = Payment(cardNumber = request.cardNumber, order = order)

        paymentPort.pay(cardNumber = payment.cardNumber!!, totalPrice = payment.order!!.getTotalPrice())
        paymentPort.save(payment)
    }
}
