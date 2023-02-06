package com.dojinyou.orderapitdd.order

import com.dojinyou.orderapitdd.ApiTest
import com.dojinyou.orderapitdd.product.ProductService
import com.dojinyou.orderapitdd.product.ProductSteps
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

class OrderApiTest: ApiTest() {

    @Autowired
    private lateinit var productService: ProductService

    @Test
    fun `상품 주문`() {
        productService.addProduct(ProductSteps.addProductRequest())

        val request = OrderSteps.createRequest()

        val response = OrderSteps.createOrder(request)

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value())
    }


}
