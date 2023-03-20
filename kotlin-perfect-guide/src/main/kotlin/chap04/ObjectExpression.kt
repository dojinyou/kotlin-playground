package chap04

class ObjectExpression

fun main() {
    fun midPoint(xRange: IntRange, yRange: IntRange) = object {
        val x = (xRange.first + xRange.last) / 2
        val y = (yRange.first + yRange.last) / 2
    }

    val midPoint1 = midPoint(1..5, 2..6)
    val midPoint2 = midPoint(1..5, 2..6)

    println(midPoint1 == midPoint2)

    val topObject = topObject()
//    println(topObject.x) // 접근이 안된다.

    var x = 1

    val o = object {
        val a = x++
    }

    println("x = $x") // 2
    println("o.a = ${o.a}") // 1
    println("x = $x") // 2
}

fun topObject() = object {
    val x = 1
    val y = 2
}
