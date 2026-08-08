package com.example.deskpet.ai

import android.content.Context
import com.example.deskpet.PetEntity
import com.example.deskpet.PetView

class ModelAdapter(private val context: Context) {
    fun bindPetToView(pet: PetEntity, petView: PetView) {
        petView.setCurrentPet(pet)
        if (pet.is3DModel) {
            petView.switchTo3DMode(pet.modelPath)
        } else {
            petView.switchTo2DFallback()
        }
    }
}

