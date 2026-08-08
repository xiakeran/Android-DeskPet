package com.example.deskpet

data class PetEntity(
    val petName: String,
    val petType: String,
    val petColor: String,
    val moveSpeed: Double,
    val bounceForce: Double,
    val sleepProbability: Double,
    val is3DModel: Boolean = false,
    val modelPath: String = ""
) {
    data class Template(
        val type: String,
        val speed: Double,
        val bounce: Double,
        val sleepRate: Double
    )
}

