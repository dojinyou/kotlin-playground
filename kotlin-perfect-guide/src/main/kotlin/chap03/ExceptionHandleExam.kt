package chap03

import java.lang.Error

class ExceptionHandleExam {

    fun test() {
        throw Error()
    }

    fun rapper() {
        test()
    }

}

fun main() {
    ExceptionHandleExam().rapper()
}
