package com.example.jetpackcalculator.ui.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(label: String,
                     onClickEvent: ()-> Unit,
                     modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = onClickEvent,
        shape = RoundedCornerShape(40.dp),
        border = BorderStroke(3.dp, Color.Gray),
        modifier = Modifier.width(90.dp).height(70.dp),
    ) {
        Text(text = label, style = TextStyle(
            fontSize = 20.sp
        ))
    }
}