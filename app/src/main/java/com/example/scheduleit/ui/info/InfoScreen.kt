package com.example.scheduleit.ui.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scheduleit.R
import com.example.scheduleit.ui.navigation.NavigationDestination
import com.example.scheduleit.ui.navigation.ScheduleItTopAppBar
import com.example.scheduleit.ui.navigation.BottomNavigationBar

object InfoDestination : NavigationDestination {
    override val route = "info"
    override val titleRes = R.string.about_app
    override val icon = Icons.Default.Info
}

@Composable
fun InfoScreen(
    onNavigateHome: () -> Unit = {},
    onViewDate: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onCollabClick: () -> Unit = {},
    onCustomizeClick: () -> Unit = {},
    onRemindersClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            ScheduleItTopAppBar(
                title = stringResource(id = InfoDestination.titleRes),
                canNavigateBack = false,
                onNavigateHome = onNavigateHome,
                onViewDate = onViewDate,
                onProfileClick = onProfileClick,
                onCollabClick = onCollabClick
            )
        },
        bottomBar = {
            BottomNavigationBar(
                onCustomizeClick = onCustomizeClick,
                onRemindersClick = onRemindersClick,
                onLogoutClick = onLogoutClick
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF2F0F5))
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Meet the Team",
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Berina Crnčalo, Orhan Teletović, Faris Isaković",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "ScheduleIt is a modern productivity and wellness planner app, developed as a final project for the Mobile Programming course.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
