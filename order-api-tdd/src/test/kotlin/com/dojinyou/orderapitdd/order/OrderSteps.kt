package com.dojinyou.orderapitdd.order

import com.dojinyou.orderapitdd.ApiTest
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response

class OrderSteps: ApiTest() {

    companion object {
        fun createOrder(request: CreateOrderRequest): ExtractableResponse<Response> {
            return RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .post("/api/orders")
                .then()
                .log().all().extract()
        }

        fun createRequest(): CreateOrderRequest {
            val productId = 1L
            val quantity = 2
            return CreateOrderRequest(productId, quantity)
        }
    }
}
