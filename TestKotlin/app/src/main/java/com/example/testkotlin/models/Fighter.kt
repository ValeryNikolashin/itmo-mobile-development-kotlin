package com.example.testkotlin.models

import java.io.Serializable

class Fighter(
    private var firstName: String,
    private var lastName: String,
    var description: String,
    var imageSource: Int,
    var type: FighterType
) : Serializable {
    override fun toString(): String {
        return "$firstName $lastName"
    }
}