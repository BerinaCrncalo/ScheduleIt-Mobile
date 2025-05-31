package com.example.scheduleit.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scheduleit.data.models.Task
import com.example.scheduleit.data.converter.DateTimeConverterUtil
import com.example.scheduleit.ui.theme.DeepPurple

@Composable
fun CompletedTaskCard(
    task: Task,
    onCheckedChange: (Task, Boolean) -> Unit,
    onDelete: (Task) -> Unit,
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    onRequestDetails: (Int) -> Unit
) {
    val isVisible by remember { mutableStateOf(true) }

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInHorizontally(
            initialOffsetX = { fullWidth -> fullWidth },
            animationSpec = tween(durationMillis = 500)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { fullWidth -> -fullWidth },
            animationSpec = tween(durationMillis = 500)
        )
    ) {
        Surface(
            elevation = 6.dp,
            shape = RoundedCornerShape(16.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .shadow(elevation = 6.dp, shape = RoundedCornerShape(16.dp))
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                backgroundColor = DeepPurple.copy(alpha = 0.15f),
                modifier = modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Checkbox(
                        checked = isChecked,
                        colors = CheckboxDefaults.colors(
                            checkedColor = DeepPurple,
                            uncheckedColor = Color.Gray
                        ),
                        onCheckedChange = { onCheckedChange(task, it) }
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp)
                            .clickable { onRequestDetails(task.id) },
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = task.title,
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Bold,
                            color = DeepPurple
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Due: ${DateTimeConverterUtil.formatDate(DateTimeConverterUtil.dateToLocalDate(task.dueDate))}",
                            style = MaterialTheme.typography.body2,
                            color = Color.DarkGray,
                            fontSize = 13.sp
                        )
                    }

                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete task",
                        tint = Color.Red,
                        modifier = Modifier
                            .clickable { onDelete(task) }
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}
