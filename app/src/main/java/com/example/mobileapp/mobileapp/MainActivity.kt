package com.example.mobileapp.mobileapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobileapp.mobileapp.ui.theme.MobileAppTheme
import kotlinx.coroutines.delay
import kotlin.system.exitProcess


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }

}
// popup menu to show before exiting the app
@Composable
fun App() {
    var showDialog by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                showDialog = true
                Log.e("RootDetected", VulnerabilityState.vulnerabilities.joinToString(", "))
            },
            enabled = !isLoading
        ) {
            Text("Check Vulnerabilities")
        }

        if (isLoading) {
            LoadingSpinner()
        }

        if (showDialog) {
            ExitDialog()
        }

        LaunchedEffect(true) {
            delay(12 * 1000) // wait for 5 seconds
            isLoading = false // enable the button
        }
    }
}


@Composable
fun LoadingSpinner() {
    CircularProgressIndicator(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .size(48.dp)
    )
}
@Composable
fun ExitDialog() {
    AlertDialog(
        onDismissRequest = {
            exitProcess(1)
        },
        title = {
            Text("Vulnerabilities Detection Result")
        },
        text = {
            if(VulnerabilityState.vulnerabilities.isEmpty())
                Text("Congratulations!! No Vulnerabilities Detected")
            else
                Text(VulnerabilityState.vulnerabilities.joinToString(", "))
        },
        confirmButton = {
            Button(onClick = { exitProcess(1) }) {
                Text("EXIT")
            }
        }
    )
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MobileAppTheme {
//        Greeting("Android")
//    }
//}
