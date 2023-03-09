package com.dojinyou.orderapitdd.product

import io.restassured.RestAssured
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import org.springframework.http.MediaType

class ProductSteps {
    companion object {
        fun requestAddProduct(request: AddProductRequest): ExtractableResponse<Response>? =
            RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .`when`()
                .post("/products")
                .then()
                .log().all().extract()

        fun addProductRequest(): AddProductRequest {
            val discountPolicy = DiscountPolicy.NONE
            val name = "상품명"
            val price = 1000L

            return AddProductRequest(name, price, discountPolicy)
        }

        fun getProduct(productId: Long): ExtractableResponse<Response> =
            RestAssured.given().log().all()
                .`when`()
                .get("/products/{productId}", productId)
                .then().log().all()
                .extract()

        fun updateProduct(productId: Long, request: UpdateProductRequest): ExtractableResponse<Response> = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .`when`()
                .put("/products/{productId}", productId)
                .then()
                .log().all().extract()
    }
}
