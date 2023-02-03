package com.dojinyou.orderapitdd.product

import com.dojinyou.orderapitdd.ApiTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus


class  ProductApiTest: ApiTest() {

    @Test
    fun `상품 등록 테스트`() {
        val request = ProductSteps.addProductRequest()

        val response = ProductSteps.requestAddProduct(request)

        assertThat(response!!.statusCode()).isEqualTo(HttpStatus.CREATED.value())
    }

    @Test
    fun `상품 조회 테스트`(){
        ProductSteps.requestAddProduct(ProductSteps.addProductRequest())
        val productId = 1L

        val response = ProductSteps.getProduct(productId)

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        assertThat(response.jsonPath().getString("id")).isEqualTo(productId.toString())
    }

    @Test
    fun `상품 수정 테스트`(){
        ProductSteps.requestAddProduct(ProductSteps.addProductRequest())
        val productId = 1L
        val updateName = "상품 수정"
        val updatePrice = 2000L
        val request = UpdateProductRequest(updateName, updatePrice, DiscountPolicy.NONE)

        val response = ProductSteps.updateProduct(productId, request)

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
    }
}

