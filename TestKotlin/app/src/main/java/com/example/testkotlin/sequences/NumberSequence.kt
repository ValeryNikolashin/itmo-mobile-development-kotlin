package com.example.testkotlin.sequences

interface NumberSequence {
    fun getNext(): Int
    fun getCurrent(): Int
    fun initBy(value: Int) {
        while (getCurrent() != value) {
            getNext()
        }
    }
}