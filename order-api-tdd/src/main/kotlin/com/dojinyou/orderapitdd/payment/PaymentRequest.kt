package com.dojinyou.orderapitdd.payment

import org.apache.logging.log4j.util.Strings

data class PaymentRequest(val orderId: Long, val cardNumber: String) {
    init {
        require(Strings.isNotBlank(cardNumber)) {"카드 번호는 비어있을 수 없습니다."}
    }
}
