package com.example.deskpet.ai

import android.content.Context
import android.graphics.Bitmap
import com.example.deskpet.PetEntity

class PhotoTo3DPetEngine(private val context: Context) {
    fun create3DPetFromPhoto(photoBitmap: Bitmap, petName: String): PetEntity {
        val randomColor = listOf("暖棕", "奶白", "墨黑").random()
        val modelPath = "${context.filesDir}/pet_3d_model/${System.currentTimeMillis()}.obj"
        return PetEntity(
            petName = petName,
            petType = "照片3D宠物",
            petColor = randomColor,
            moveSpeed = 2.3,
            bounceForce = 2.1,
            sleepProbability = 0.3,
            is3DModel = true,
            modelPath = modelPath
        )
    }
}

