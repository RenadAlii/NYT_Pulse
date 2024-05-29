package com.renad.nytpulse.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.renad.nytpulse.ui.navigation.NavHost
import com.renad.nytpulse.ui.theme.NYTPulseTheme

@Composable
fun NYTPlusApp(finishActivity: () -> Unit) {
    NYTPulseTheme {
        val navController = rememberNavController()
        Scaffold { innerPaddingModifier ->
            NavHost(
                finishActivity = finishActivity,
                navController = navController,
                modifier = Modifier.padding(innerPaddingModifier),
            )
        }
    }
}
