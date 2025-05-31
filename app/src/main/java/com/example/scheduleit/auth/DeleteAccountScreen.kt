package com.example.scheduleit.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.scheduleit.R
import com.example.scheduleit.ui.navigation.NavigationDestination
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import com.google.firebase.auth.FirebaseAuth

object DeleteAccountDestination : NavigationDestination {
    override val route = "delete_account"
    override val titleRes = R.string.delete_account
    override val icon = Icons.Default.Delete
}

@Composable
fun DeleteAccountScreen(navController: NavController) {
    var isDeleting by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Delete Account",
            style = MaterialTheme.typography.h5
        )
        Text(
            text = "Are you sure you want to delete your account? This action cannot be undone.",
            style = MaterialTheme.typography.body1
        )

        errorMessage?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.body1
            )
        }

        if (isDeleting) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .size(48.dp),
                color = MaterialTheme.colors.primary
            )
        } else {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        isDeleting = true
                        errorMessage = null

                        val user = FirebaseAuth.getInstance().currentUser
                        if (user != null) {
                            user.delete()
                                .addOnCompleteListener { task ->
                                    isDeleting = false
                                    if (task.isSuccessful) {
                                        println("Account deleted")
                                        navController.navigate("login") {
                                            popUpTo(0) { inclusive = true }
                                        }
                                    } else {
                                        errorMessage = task.exception?.message ?: "Account deletion failed"
                                    }
                                }
                        } else {
                            isDeleting = false
                            errorMessage = "No user signed in"
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Delete")
                }

                OutlinedButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancel")
                }
            }
        }
    }
}
