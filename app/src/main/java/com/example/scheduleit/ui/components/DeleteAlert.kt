package com.example.scheduleit.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.scheduleit.R
import com.example.scheduleit.ui.theme.DeepPurple
import com.example.scheduleit.ui.theme.DarkIndigo
import com.example.scheduleit.ui.theme.LightBackground

@Composable
fun DeleteAlert(
    onDelete: () -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        containerColor = LightBackground,
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = stringResource(R.string.delete_task),
                style = MaterialTheme.typography.headlineSmall,
                color = DeepPurple
            )
        },
        text = {
            Text(
                text = stringResource(R.string.are_you_sure),
                style = MaterialTheme.typography.bodyMedium,
                color = DarkIndigo
            )
        },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD11A2A)),
                onClick = onDelete
            ) {
                Text(
                    text = stringResource(R.string.delete_button),
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White
                )
            }
        },
        dismissButton = {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = DeepPurple),
                onClick = onDismissRequest
            ) {
                Text(
                    text = stringResource(R.string.close_button),
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White
                )
            }
        }
    )
}
