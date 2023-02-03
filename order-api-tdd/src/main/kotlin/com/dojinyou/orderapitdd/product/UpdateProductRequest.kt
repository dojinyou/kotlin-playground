package com.dojinyou.orderapitdd.product

data class UpdateProductRequest(val name: String, val price: Long, val discountPolicy: DiscountPolicy) {
    init {
        require(name.isNotBlank()) { "상품명은 필수입니다." }
        require(price > 0) { "가격은 음수일 수 없습니다." }
    }
}

