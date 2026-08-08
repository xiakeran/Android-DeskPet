package com.example.deskpet

import android.content.Context
import android.util.AttributeSet
import android.view.View

class PetView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var currentPet: PetEntity? = null
    private var use3DRender = false
    private var current3DPath = ""

    fun setCurrentPet(entity: PetEntity) {
        currentPet = entity
        invalidate()
    }

    fun switchTo3DMode(path: String) {
        use3DRender = true
        current3DPath = path
        invalidate()
    }

    fun switchTo2DFallback() {
        use3DRender = false
        invalidate()
    }

    // 后续可重写onDraw绘制宠物图形
}

