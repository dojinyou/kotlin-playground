package com.dojinyou.orderapitdd.product

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ProductTest {

    @Test
    fun updateProduct() {
        val product = Product(name = "상품명", price = 1000L, discountPolicy = DiscountPolicy.NONE)
        product.assignId(1L)

        val updateName = "새로운 상품명"
        val updatePrice = 2000L
        val updateDiscountPolicy = DiscountPolicy.NONE

        product.update(updateName, updatePrice, updateDiscountPolicy)
    }
}
