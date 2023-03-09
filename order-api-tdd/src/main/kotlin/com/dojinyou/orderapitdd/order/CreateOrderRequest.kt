package com.dojinyou.orderapitdd.order

data class CreateOrderRequest(val productId: Long, val quantity: Int) {
    init {
        require(quantity > 0) { "수량은 0보다 작을 수 없습니다." }
    }
}
