package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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