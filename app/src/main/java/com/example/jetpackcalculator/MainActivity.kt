package com.example.jetpackcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcalculator.ui.theme.JetPackCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorScreen()
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorTextInput() {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    TextField(
        value = textState.value,
        onValueChange = { textState.value = it }
    )
}

@Composable
fun CalCulatorButton(label: String, onClickEvent: ()-> Unit ,modifier: Modifier = Modifier) {
    Button(
        onClick = onClickEvent,
    ) {
       Text(text = label)
    }
}

@Composable
fun CalculatorButtonContainer(modifier: Modifier = Modifier, onClickEvent: ()-> Unit) {
    Column() {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CalCulatorButton(label = "C", onClickEvent)
            CalCulatorButton(label = "()", onClickEvent)
            CalCulatorButton(label = "%", onClickEvent)
            CalCulatorButton(label = "/", onClickEvent)
        }

        Row() {
            CalCulatorButton(label = "7", onClickEvent)
            CalCulatorButton(label = "8", onClickEvent)
            CalCulatorButton(label = "X", onClickEvent)
        }

        Row() {
            CalCulatorButton(label = "4", onClickEvent)
            CalCulatorButton(label = "5", onClickEvent)
            CalCulatorButton(label = "6", onClickEvent)
            CalCulatorButton(label = "-", onClickEvent)
        }

        Row() {
            CalCulatorButton(label = "1", onClickEvent)
            CalCulatorButton(label = "2", onClickEvent)
            CalCulatorButton(label = "3", onClickEvent)
            CalCulatorButton(label = "+", onClickEvent)
        }

        Row() {
            CalCulatorButton(label = "+/-", onClickEvent)
            CalCulatorButton(label = "0", onClickEvent)
            CalCulatorButton(label = ".", onClickEvent)

        }
    }
}

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier){
 Column {
     CalculatorTextInput()
     //CalculatorButtonContainer()
 }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetPackCalculatorTheme {
        CalculatorScreen()
    }
}