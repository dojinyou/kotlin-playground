package chap04

data class SampleData(
    val id: Int = 0,
    val message: String? = null,
) {
    init {
        println("init $this")
    }

    constructor(data: OtherData) : this(data.id, data.name) {
        println("constructor $this")
    }
}
