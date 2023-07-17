package com.example.jetpackcalculator.ui.view.components

import android.opengl.Visibility
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcalculator.model.CalculatorState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorTextInput(value: CalculatorState) {
    val scroll = rememberScrollState(0)
    Column {
        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.20f),
            Arrangement.End) {

            OutlinedTextField(value = value.equation,
                onValueChange = {
                    value.equation = it
                },
                textStyle = TextStyle(
                    fontSize = 24.sp,
                    color = Color.DarkGray,
                    background = Color.Transparent
                ),
                label = {Text(text = "Enter equation")},
                placeholder = {Text(text = "0.00")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .height(100.dp)

            )
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            Arrangement.End) {
            Text(text = value.result,
                style = TextStyle(
                    fontSize = 22.sp,
                    color = Color.Gray
                )
            )
        }
    }
}