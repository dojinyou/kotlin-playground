package com.dojinyou.orderapitdd.payment

import com.dojinyou.orderapitdd.ApiTest
import com.dojinyou.orderapitdd.order.OrderService
import com.dojinyou.orderapitdd.order.OrderSteps
import com.dojinyou.orderapitdd.product.ProductService
import com.dojinyou.orderapitdd.product.ProductSteps
import io.restassured.RestAssured
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType


class PaymentApiTest: ApiTest() {

    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var orderService: OrderService

    @Test
    fun `결제 요청`() {
        productService.addProduct(ProductSteps.addProductRequest())
        orderService.createOrder(OrderSteps.createRequest())

        val request = PaymentStep.createPaymentRequest()

        val response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .`when`()
                .post("/api/payments")
                .then()
                .log().all().extract()

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
    }

}
