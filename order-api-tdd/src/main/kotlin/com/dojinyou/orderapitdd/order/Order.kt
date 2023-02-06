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
    init {
        require(quantity!! > 0) {"주문 수량은 0보다 커야 합니다"}
    }
}
