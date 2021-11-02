package com.example.testkotlin.sequences

class FibonacciSequence : NumberSequence {
    private var currentNumber = 1
    private var nextNumber = 2

    override fun getNext(): Int {
        val swap = nextNumber
        nextNumber += currentNumber
        currentNumber = swap

        return currentNumber
    }

    override fun getCurrent(): Int {
        return currentNumber
    }
}