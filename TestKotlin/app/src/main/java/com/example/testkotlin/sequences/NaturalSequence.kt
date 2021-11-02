package com.example.testkotlin.sequences

class NaturalSequence : NumberSequence {
    private var sequence = arrayListOf(2)

    override fun getNext(): Int {
        var nextNumber = sequence.last()

        while (isNotNatural(nextNumber)) {
            nextNumber++
        }

        sequence.add(nextNumber)

        return nextNumber
    }

    override fun getCurrent(): Int {
        return sequence.last()
    }

    private fun isNotNatural(number: Int): Boolean {
        if (sequence.any { x -> number % x == 0 })
            return true

        return false
    }
}