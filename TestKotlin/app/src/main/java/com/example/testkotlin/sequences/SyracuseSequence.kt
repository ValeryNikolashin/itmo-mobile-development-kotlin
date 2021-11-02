package com.example.testkotlin.sequences

class SyracuseSequence(private var currentNumber:Int) : NumberSequence {
    override fun getNext(): Int {
        if (currentNumber % 2 == 0)
            currentNumber /= 2
        else
            currentNumber = currentNumber * 3 + 1

        return currentNumber
    }

    override fun getCurrent(): Int {
        return currentNumber
    }
}