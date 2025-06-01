package com.example.scheduleit.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.min

@Composable
fun PieChart(data: List<Float>, modifier: Modifier = Modifier.size(200.dp)) {
    val colors = listOf(
        Color(0xFF000000), // Black
        Color(0xFFFFFFFF), // White
        Color(0xFF00BCD4), // Cyan-ish
        Color(0xFF3F51B5), // Indigo
        Color(0xFF448AFF), // Blue Accent
        Color(0xFF563FA2), // Purple
        Color(0xFFE9E4F5)  // Light Lavender
    )

    val total = data.sum()
    var startAngle = -90f

    Canvas(modifier = modifier) {
        val diameter = min(size.width, size.height)
        val radius = diameter / 2f
        val rect = Rect(0f, 0f, diameter, diameter)

        data.forEachIndexed { index, value ->
            val sweepAngle = (value / total) * 360f
            drawArc(
                color = colors[index % colors.size],
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = true,
                topLeft = rect.topLeft,
                size = rect.size,
                style = Fill
            )
            startAngle += sweepAngle
        }

        // White circle in center for donut style
        drawCircle(
            color = Color.White,
            radius = radius / 2f,
            center = rect.center
        )

        // Border circle around pie
        drawCircle(
            color = Color.Gray,
            radius = radius,
            center = rect.center,
            style = Stroke(width = 2f)
        )
    }
}
