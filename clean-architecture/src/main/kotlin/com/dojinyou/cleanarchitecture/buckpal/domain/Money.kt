package com.dojinyou.cleanarchitecture.buckpal.domain

class Money(
    val amount: Long,
) {
    fun negative(): Money {
        return Money(-amount)
    }

    fun isPositive(): Boolean {
        return amount >= 0
    }


    companion object {
        fun add(money1: Money, money2: Money): Money {
            val totalAmount = money1.amount + money2.amount
            return Money(totalAmount)
        }
    }

}
