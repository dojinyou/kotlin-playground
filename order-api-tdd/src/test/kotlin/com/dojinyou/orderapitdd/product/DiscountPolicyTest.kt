package com.dojinyou.orderapitdd.product

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DiscountPolicyTest {

    @Test
    fun applyDiscount() {
        val price = 1000
        val discountPrice = DiscountPolicy.NONE.applyDiscount(price)

        Assertions.assertThat(discountPrice).isEqualTo(price)
    }
    
    @Test
    fun applyDiscountFix() {
        val price = 1000
        val discountPrice = DiscountPolicy.FIX_1000_AMOUNT.applyDiscount(price)

        Assertions.assertThat(discountPrice).isEqualTo(price - 1000)
    }

    @Test
    fun applyDiscountFix2() {
        val price = 500
        val discountPrice = DiscountPolicy.FIX_1000_AMOUNT.applyDiscount(price)

        Assertions.assertThat(discountPrice).isEqualTo(0)
    }
}
