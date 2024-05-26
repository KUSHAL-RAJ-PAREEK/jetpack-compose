package com.krp.viewswithcompose

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val composeLayout = findViewById<ComposeView>(R.id.compose_layout)
        composeLayout.setContent {  
            saycheezy(name = "kushal")
        }
        }
    }


@Composable
fun saycheezy(name : String ){
    Text(text = "Hello $name")
}