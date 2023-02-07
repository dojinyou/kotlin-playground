package com.dojinyou.orderapitdd.order

import com.dojinyou.orderapitdd.product.DiscountPolicy
import com.dojinyou.orderapitdd.product.Product
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class OrderTest {

    @Test
    fun successGetTotalPrice() {
        val order = Order(quantity = 2, product = Product(name = "name", price = 2000L, discountPolicy = DiscountPolicy.FIX_1000_AMOUNT))

        val totalPrice = order.getTotalPrice()

        Assertions.assertThat(totalPrice).isEqualTo(2000)
    }
}
