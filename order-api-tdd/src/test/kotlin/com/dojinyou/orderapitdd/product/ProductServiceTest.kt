package com.dojinyou.orderapitdd.product

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus

@SpringBootTest
class ProductServiceTest {

    @Autowired
    lateinit var productService: ProductService

    @Test
    fun `상품 조회`() {
        // 상품 등록
        productService.addProduct(ProductSteps.addProductRequest())
        val productId = 1L

        // 상품 조회
        val response = productService.getProduct(productId)

        // 상품의 응답을 검증
        assertThat(response).isNotNull()
    }

    @Test
    fun `상품 수정`() {
        // 상품 등록
        productService.addProduct(ProductSteps.addProductRequest())
        val productId = 1L
        val updateName = "상품 수정"
        val updatePrice = 2000L
        val request = UpdateProductRequest(updateName, updatePrice, DiscountPolicy.NONE)

        productService.updateProduct(productId, request)

        // 상품 조회
        val response = productService.getProduct(productId)

        // 변경사항 비교
        assertThat(response.statusCode.value()).isEqualTo(HttpStatus.OK.value())
        assertThat(response.body!!.name).isEqualTo(updateName)
        assertThat(response.body!!.price).isEqualTo(updatePrice)
    }
}
