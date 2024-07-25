package com.example.myapplication

import android.os.Bundle

import androidx.activity.*
import androidx.activity.compose.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.style.TextOverflow

import com.example.myapplication.ui.theme.MyComposeApplicationTheme

import dev.trindadedev.lib.ui.components.preferences.compose.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var switchValue by remember { mutableStateOf(false) }

                    Column {
                        Preference(
                            iconResId = R.drawable.ic_settings_24,
                            title = "Settings",
                            summary = "Customize app with app settings",
                            onClick = {
                                // Ação ao clicar no Preference
                            }
                        )

                        PreferenceSwitch(
                            iconResId = R.drawable.ic_settings_24,
                            title = "Night mode",
                            summary = "Activate night mode for night",
                            isSwitchChecked = switchValue,
                            onSwitchCheckedChange = { isChecked ->
                                switchValue = isChecked
                            }
                        )
                    }
                }
            }
        }
    }
}