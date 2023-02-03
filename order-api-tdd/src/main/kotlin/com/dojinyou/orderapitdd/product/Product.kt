package com.dojinyou.orderapitdd.product

import javax.persistence.*

@Entity
@Table(name = "products")
class Product(
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @get:Column(nullable = false)
    var name: String?,

    @get:Column(nullable = false)
    var price: Long?,

    @get:Column(nullable = false)
    @get:Enumerated(value = EnumType.STRING)
    var discountPolicy: DiscountPolicy?
) {
    fun assignId(id: Long) {
        this.id = id
    }

    fun update(name: String, price: Long, discountPolicy: DiscountPolicy) {
        require(name.isNotBlank())
        require(price > 0)
        this.name = name
        this.price = price
        this.discountPolicy = discountPolicy
    }
}
