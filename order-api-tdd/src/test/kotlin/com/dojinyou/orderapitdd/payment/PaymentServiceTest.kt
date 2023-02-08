package com.dojinyou.orderapitdd.payment

import com.dojinyou.orderapitdd.order.Order
import com.dojinyou.orderapitdd.order.OrderSteps
import com.dojinyou.orderapitdd.product.DiscountPolicy
import com.dojinyou.orderapitdd.product.Product
import com.dojinyou.orderapitdd.product.ProductSteps
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class PaymentServiceTest {

    @InjectMocks
    private lateinit var paymentService: PaymentService

    @Mock
    private lateinit var paymentPort: PaymentPort


    @Test
    fun `결제 요청`() {
        val product = Product(1L, "name", 1000L, DiscountPolicy.NONE)
        val order = Order(1L, 2, product)
        `when`(paymentPort.getOrderById(anyLong())).thenReturn(order)

        val request = PaymentStep.createPaymentRequest()

        paymentService.payment(request)
    }

}
