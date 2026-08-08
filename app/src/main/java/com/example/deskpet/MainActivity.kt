package com.example.deskpet

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.deskpet.ai.ModelAdapter
import com.example.deskpet.ai.PetAIGenerator
import com.example.deskpet.ai.PhotoTo3DPetEngine

class MainActivity : AppCompatActivity() {

    private lateinit var petView: PetView
    private lateinit var aiGenerator: PetAIGenerator
    private lateinit var photoEngine: PhotoTo3DPetEngine
    private lateinit var modelAdapter: ModelAdapter

    companion object {
        private const val REQUEST_GALLERY = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        petView = findViewById(R.id.petView)
        aiGenerator = PetAIGenerator()
        photoEngine = PhotoTo3DPetEngine(this)
        modelAdapter = ModelAdapter(this)

        findViewById<Button>(R.id.btn_ai_generate).setOnClickListener {
            showAiInputDialog()
        }

        findViewById<Button>(R.id.btn_photo_3d).setOnClickListener {
            openGallery()
        }

        findViewById<Button>(R.id.btn_random).setOnClickListener {
            val randomPet = aiGenerator.randomGenerate()
            modelAdapter.bindPetToView(randomPet, petView)
        }
    }

    private fun showAiInputDialog() {
        val editText = EditText(this)
        AlertDialog.Builder(this)
            .setTitle("输入宠物描述")
            .setView(editText)
            .setPositiveButton("生成") { _, _ ->
                val text = editText.text.toString()
                if (text.isNotBlank()) {
                    val pet = aiGenerator.generateByPrompt(text)
                    modelAdapter.bindPetToView(pet, petView)
                }
            }
            .setNegativeButton("取消", null)
            .show()
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            val uri = data?.data ?: return
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
            create3DPetFromImage(bitmap)
        }
    }

    private fun create3DPetFromImage(bitmap: Bitmap) {
        val editText = EditText(this)
        AlertDialog.Builder(this)
            .setTitle("给3D宠物命名")
            .setView(editText)
            .setPositiveButton("确定") { _, _ ->
                val name = editText.text.toString().ifBlank { "照片宠物" }
                val pet = photoEngine.create3DPetFromPhoto(bitmap, name)
                modelAdapter.bindPetToView(pet, petView)
            }
            .show()
    }
}

