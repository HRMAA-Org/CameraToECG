package com.adikul.camerademo


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.adikul.camerademo.databinding.ActivityGraphAnalyzeBinding
import com.chaquo.python.PyException
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.*

class GraphAnalyzeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGraphAnalyzeBinding
    lateinit var py: Python
    lateinit var module: PyObject
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGraphAnalyzeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.Default){
            if (!Python.isStarted()) {
                Python.start(AndroidPlatform(this@GraphAnalyzeActivity))
            }
            py = Python.getInstance()
            module = py.getModule("ecg_to_data")
            Log.d("Chaquopy", "Python set up")

            val path = intent.getStringExtra("graph_path").toString()
            convertEcgToData(path)
        }

    }

    private fun convertEcgToData(path: String) {
        try {
            val arr = module.callAttr("line_detector", path).asList()
            val data = arr.toString()
            val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val file = File(folder, "values.txt")
            val fos= FileOutputStream(file)
            fos.write(data.toByteArray())
            runOnUiThread {
                Toast.makeText(
                    this@GraphAnalyzeActivity,
                    "File stored at $folder",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("File", "$folder")
                binding.textView.text = arr.size.toString()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            Log.d("Python", "File not found")
            runOnUiThread {
                Toast.makeText(
                    this,
                    "File not found",
                    Toast.LENGTH_SHORT
                ).show()
            }

        } catch (e: PyException) {
            runOnUiThread {
                Toast.makeText(
                    this@GraphAnalyzeActivity,
                    "Could not run Python script",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Log.e(
                "Error in python script",
                e.message + "\n" + e.cause + "\n" + e.toString()
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}