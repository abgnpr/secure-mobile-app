package com.tcs.ngm.banking.mobileapp

import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.scottyab.rootbeer.RootBeer
import com.tcs.ngm.banking.mobileapp.ui.theme.MobileAppTheme


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
                    Greeting("Android")
                    RootCheckButton()
                }
            }
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun RootCheckButton(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Button(
        onClick = {
            val rootBeer = RootBeer(context)
            if (rootBeer.isRooted) {
                Toast.makeText(context, "rooted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "not rooted", Toast.LENGTH_SHORT).show()
            }
        },
        Modifier
            .height(48.dp)
            .width(200.dp)
            .wrapContentHeight()
            .wrapContentWidth()
    ) {
        Text(text = "Check Root")
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MobileAppTheme {
//        Greeting("Android")
//    }
//}
