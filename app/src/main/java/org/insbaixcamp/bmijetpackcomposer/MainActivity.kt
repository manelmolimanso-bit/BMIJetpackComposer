package org.insbaixcamp.bmijetpackcomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.insbaixcamp.bmijetpackcomposer.ui.theme.BMIJetpackComposerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMIJetpackComposerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BMIScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun BMIScreen(modifier: Modifier = Modifier) {
    var name: String by remember { mutableStateOf("Josep Maria") }
    var pes: Int by remember { mutableIntStateOf(80) }
    var alçada: Float by remember { mutableFloatStateOf(180f) }
    var bmi: Float by remember { mutableFloatStateOf(0f) }

    Column(modifier = modifier) {
        Text(text = "BMI Calculator")
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        Text(text = "Pes")
        Row {
            Text(text = "$pes Kg")
            Button(onClick = {
                pes++
            }) {
                Text(text = "+")
            }
            Button(onClick = {
                pes--
            }) {
                Text(text = "-")
            }
        }

        Text(text = "${alçada.toInt()} cm")
        Slider(
            value = alçada,
            onValueChange = { alçada = it },
            valueRange = 100f..220f
        )
        Button(
            onClick = {
                val alçadaMetres = alçada / 100f
                bmi = pes.toFloat() / (alçadaMetres * alçadaMetres)
            }
        ) {
            Text(text = "Calcular BMI")
        }
        if (bmi != 0f) {
            Text(text = "El teu BMI és: ${String.format("%.2f", bmi)}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BMIScreenPreview() {
    BMIJetpackComposerTheme {
        BMIScreen()
    }
}