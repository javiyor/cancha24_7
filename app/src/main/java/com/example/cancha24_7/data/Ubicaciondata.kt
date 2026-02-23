package com.example.cancha24_7.data

object Ubicaciondata {

    val paises = listOf("Argentina")

    val provinciasPorPais = mapOf(
        "Argentina" to listOf(
            "Santa Fe",
            "Buenos Aires",
            "Córdoba"
        )
    )

    val ciudadesPorProvincia = mapOf(
        "Santa Fe" to listOf(
            "Reconquista",
            "Santa Fe Capital",
            "Rosario"
        ),
        "Buenos Aires" to listOf(
            "La Plata",
            "Mar del Plata"
        ),
        "Córdoba" to listOf(
            "Córdoba Capital"
        )
    )
}
