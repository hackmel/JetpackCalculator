package com.example.jetpackcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.jetpackcalculator.ui.theme.JetPackCalculatorTheme
import com.example.jetpackcalculator.ui.view.CalculatorScreen
import com.example.jetpackcalculator.viewmodel.CalculatorViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackCalculatorTheme {
                val viewModel: CalculatorViewModel by viewModels()
                val state = viewModel.state

                CalculatorScreen(state = state, onClickEvent = viewModel::getCalculatorAction)

            }
        }
    }
}





