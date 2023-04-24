package chap04

class Test

fun main() {
    val data = OtherData(2, "other")
    val sample1 = SampleData(1, null)
    val sample2 = SampleData(data)

    println("sample1 = $sample1")
    println("sample2 = $sample2")

    // UTF-8
    val targetString = "é"

}
