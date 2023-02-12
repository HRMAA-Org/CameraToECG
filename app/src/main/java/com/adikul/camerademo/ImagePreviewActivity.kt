package com.adikul.camerademo

import android.R
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adikul.camerademo.databinding.ActivityImagePreviewBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException


class ImagePreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImagePreviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagePreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra("name").toString()

        loadImageFromStorage(name)


    }

    private fun loadImageFromStorage(name: String) {
        try {
            val f = File("${Environment.getExternalStorageDirectory().absolutePath}/Pictures/CameraToECG/$name.jpg")
            val b = BitmapFactory.decodeStream(FileInputStream(f))
            val img: ImageView = binding.imgIvIpa
            img.setImageBitmap(b)
            binding.retakeFabIpa.setOnClickListener {
                f.delete()
                Toast.makeText(
                    this,
                    "Photo deleted successfully",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this@ImagePreviewActivity, MainActivity::class.java)
                startActivity(intent)
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            Log.d("ImgPrev", "File $name.jpg not found")
            Toast.makeText(
                this,
            "File $name.jpg not found",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}