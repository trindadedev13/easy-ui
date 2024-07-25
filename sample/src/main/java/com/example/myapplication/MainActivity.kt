package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyComposeApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   Preference(
                      iconResId = R.drawable.ic_settings_24,
                      title = "Settings",
                      summary = "Customize app with app settings",
                      onClick = {
                         
                      }
                   )
                   PreferenceSwitch(
                      iconResId = R.drawable.ic_settings_24,
                      title = "Night mode",
                      summary = "Active night mode for night",
                      isSwitchChecked = false,
                      onSwitchCheckedChange = { isChecked ->
                      
                      }
                   )
                }
            }
        }
    }
}