package com.dojinyou.orderapitdd.payment

import com.dojinyou.orderapitdd.order.Order
import javax.persistence.*

@Entity
@Table(name = "payments")
data class Payment(

    @get:Id
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @get:Column
    var cardNumber: String? = null,

    @get:OneToOne
    var order: Order? = null
) {
}
