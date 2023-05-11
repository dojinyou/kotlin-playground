package com.dojinyou.cleanarchitecture.buckpal.domain

import java.time.Instant

data class Activity(
    val accountId: AccountId,
    val fromAccountId: AccountId,
    val toAccountId: AccountId,
    val createdAt: Instant,
    val money: Money,
)
