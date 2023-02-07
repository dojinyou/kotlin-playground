package com.dojinyou.orderapitdd.product

import kotlin.math.max

enum class DiscountPolicy {
    NONE {
        override fun applyDiscount(price: Int): Int {
            return price
        }
    },

    FIX_1000_AMOUNT {
        override fun applyDiscount(price: Int): Int {
            return max(price - 1000, 0)
        }
    };

    abstract fun applyDiscount(price: Int): Int
}
