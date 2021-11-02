package com.example.testkotlin.datasources

import com.example.testkotlin.R
import com.example.testkotlin.models.Fighter
import com.example.testkotlin.models.FighterType


class FighterDataSource {
    fun fetchData(): ArrayList<Fighter> {
        val users = arrayListOf<Fighter>()
        users.add(
            Fighter(
                "Ali",
                "Muhammad",
                "Professional boxer",
                R.drawable.ali,
                FighterType.Boxing
            )
        )
        users.add(
            Fighter(
                "Mike",
                "Tyson",
                "Professional boxer",
                R.drawable.tyson,
                FighterType.Boxing
            )
        )
        users.add(
            Fighter(
                "Conor",
                "McGregor",
                "Professional UFC fighter",
                R.drawable.mcgregor,
                FighterType.MMA
            )
        )
        users.add(
            Fighter(
                "Alexander",
                "Usik",
                "Professional boxer",
                R.drawable.usik,
                FighterType.Boxing
            )
        )
        users.add(
            Fighter(
                "Vasiliy",
                "Lomachenko",
                "Professional boxer",
                R.drawable.lomachenko,
                FighterType.Boxing
            )
        )
        users.add(
            Fighter(
                "Khabib",
                "Nurmagomedov",
                "Professional UFC fighter",
                R.drawable.nurmagomedov,
                FighterType.MMA
            )
        )
        users.add(
            Fighter(
                "Jon",
                "Jones",
                "Professional UFC fighter",
                R.drawable.jones,
                FighterType.MMA
            )
        )
        return users
    }
}