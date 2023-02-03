package com.dojinyou.orderapitdd.product

data class GetProductResponse(
    val id: Long,
    val name: String,
    val price: Long,
    val discountPolicy: DiscountPolicy
) {
    init {
        require(id > 0)
        require(name.isNotBlank())
        require(price > 0)
    }
}
