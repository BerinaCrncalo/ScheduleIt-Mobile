package com.example.scheduleit.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
        backgroundColor = LightBackground,
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = stringResource(R.string.delete_task),
                style = MaterialTheme.typography.h6,
                color = DeepPurple
            )
        },
        text = {
            Text(
                text = stringResource(R.string.are_you_sure),
                style = MaterialTheme.typography.body1,
                color = DarkIndigo
            )
        },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFD11A2A)),
                onClick = onDelete
            ) {
                Text(
                    text = stringResource(R.string.delete_button),
                    style = MaterialTheme.typography.button,
                    color = Color.White
                )
            }
        },
        dismissButton = {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = DeepPurple),
                onClick = onDismissRequest
            ) {
                Text(
                    text = stringResource(R.string.close_button),
                    style = MaterialTheme.typography.button,
                    color = Color.White
                )
            }
        }
    )
}
