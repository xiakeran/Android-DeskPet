package com.example.deskpet.ai

import com.example.deskpet.PetEntity
import kotlin.random.Random

class PetAIGenerator {
    private val petTemplates = mapOf(
        "猫咪" to PetEntity.Template("猫", speed = 2.2, bounce = 1.8, sleepRate = 0.4),
        "狗狗" to PetEntity.Template("狗", speed = 2.8, bounce = 1.2, sleepRate = 0.2),
        "兔子" to PetEntity.Template("兔", speed = 3.0, bounce = 2.5, sleepRate = 0.35)
    )

    fun generateByPrompt(prompt: String): PetEntity {
        val lowerPrompt = prompt.lowercase()
        val baseTemplate = when {
            lowerPrompt.contains("猫") -> petTemplates["猫咪"]
            lowerPrompt.contains("狗") -> petTemplates["狗狗"]
            lowerPrompt.contains("兔") -> petTemplates["兔子"]
            else -> petTemplates["猫咪"]
        } ?: petTemplates["猫咪"]!!

        var finalSpeed = baseTemplate.speed
        var finalBounce = baseTemplate.bounce
        var finalSleep = baseTemplate.sleepRate

        if (lowerPrompt.contains("慵懒")) {
            finalSpeed *= 0.6
            finalSleep += 0.2
        }
        if (lowerPrompt.contains("好动")) {
            finalSpeed *= 1.3
            finalBounce *= 1.2
        }

        val colorList = listOf("橘色", "纯白", "灰色")
        val petColor = colorList[Random.nextInt(colorList.size)]

        return PetEntity(
            petName = prompt,
            petType = baseTemplate.type,
            petColor = petColor,
            moveSpeed = finalSpeed,
            bounceForce = finalBounce,
            sleepProbability = finalSleep
        )
    }

    fun randomGenerate(): PetEntity {
        return generateByPrompt(listOf("慵懒小猫", "活泼小狗", "软萌兔子").random())
    }
}

