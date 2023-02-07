package com.dojinyou.orderapitdd.payment

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.mock.mockito.MockBean


@ExtendWith(MockitoExtension::class)
class PaymentServiceTest {

    @InjectMocks
    private lateinit var paymentService: PaymentService

    @MockBean
    private lateinit var paymentPort: PaymentPort

    @MockBean
    private lateinit var paymentGateway: PaymentGateway


    @Test
    fun `결제 요청`() {
        val request = createPaymentRequest()

        paymentService.payment(request)
    }

    private fun createPaymentRequest(): PaymentRequest {
        val orderId = 1L
        val cardNumber = ""
        return PaymentRequest(orderId, cardNumber)
    }

}
