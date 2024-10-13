package com.nelalexxx.sharedprefs

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nelalexxx.sharedprefs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // mode depend to access the other apps to your data
        val sharedPref = getSharedPreferences("myPref", MODE_PRIVATE)
        val editor = sharedPref.edit()

        binding.btnSave.setOnClickListener {
            val name = binding.edText.text.toString()
            editor.apply {
                putString("name", name)
                apply() // the same as commit() but async
            }

        }

        binding.btnLoad.setOnClickListener {
            val name = sharedPref.getString("name", "")

            binding.edText.setText(name)

        }

    }
}