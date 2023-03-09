fun main(args: Array<String>) {
    val a = 1
    var b = 0
    val result = calculate(a, b)
}

fun calculate(a:Int, b:Int) = when {
    a > b -> println("big a")
    b > a -> println("big b")
}
