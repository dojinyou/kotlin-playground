package com.dojinyou.cleanarchitecture.buckpal.domain

class ActivityWindow {
    private val activityList: MutableList<Activity> = emptyList<Activity>().toMutableList()

    fun calculateBalance(id: AccountId): Money {
        var balance: Long = 0

        activityList.forEach {
            balance += it.money.amount
        }

        return Money(balance)
    }

    fun addActivity(activity: Activity) {
        activityList.add(activity)
    }
}
