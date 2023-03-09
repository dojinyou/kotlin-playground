package com.dojinyou.orderapitdd.order

import com.dojinyou.orderapitdd.product.ProductRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class OrderServiceTest {

    @InjectMocks
    private lateinit var orderService: OrderService

    @Mock
    private lateinit var orderRepository: OrderRepository

    @Mock
    private lateinit var productRepository: ProductRepository

    @Mock
    private lateinit var port: OrderPort

    @Test
    fun `상품 주문`() {
        val productId = 1L
        val quantity = 2
        val request = CreateOrderRequest(productId, quantity)

        orderService.createOrder(request)
    }

}
