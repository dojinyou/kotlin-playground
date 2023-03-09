package com.dojinyou.orderapitdd.order

import com.dojinyou.orderapitdd.product.Product
import javax.persistence.*

/**
 *
 */
@Entity
@Table(name = "orders")
data class Order(
    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @get:Column(nullable = false)
    var quantity: Int?,

    @get:OneToOne
    var product: Product?
) {

    @Transient
    fun getTotalPrice(): Int = product!!.getDiscountPrice() * quantity!!
}
