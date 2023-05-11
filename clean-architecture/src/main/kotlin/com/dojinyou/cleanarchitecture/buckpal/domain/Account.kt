package com.dojinyou.cleanarchitecture.buckpal.domain

import java.time.Instant

class Account(
    val id: AccountId,
    val baselineBalance: Money,
    val activityWindow: ActivityWindow,
) {
    fun calculateBalance(): Money {
        return Money.add(
            baselineBalance,
            activityWindow.calculateBalance(id)
        )
    }

    fun withdraw(money: Money, targetAccountId: AccountId): Boolean {
        if (mayWithdraw(money).not()) return false

        val now = Instant.now()
        val withdrawal = Activity(id, id, targetAccountId, now, money)
        activityWindow.addActivity(withdrawal)
        return true
    }

    fun deposit(money: Money, sourceAccountId: AccountId): Boolean {
        val deposit = Activity(id, sourceAccountId, id, Instant.now(), money)
        activityWindow.addActivity(deposit)
        return true
    }

    private fun mayWithdraw(money: Money): Boolean {
        return Money.add(calculateBalance(), money.negative()).isPositive()
    }
}
