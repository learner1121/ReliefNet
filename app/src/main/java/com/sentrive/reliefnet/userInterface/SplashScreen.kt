package com.sentrive.reliefnet.userInterface

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sentrive.reliefnet.R
import com.sentrive.reliefnet.ui.theme.cantataOne
import com.sentrive.reliefnet.ui.theme.cantataOneFontFamily
import com.sentrive.reliefnet.ui.theme.inriaSerifFontFamily

@Composable
fun SplashScreen(navHostController: NavHostController) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Foreground content
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(bottom = 300.dp),
            contentAlignment = Alignment.Center
        ) {
            LogoWithText()
        }
        LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(1500)
            navHostController.navigate("LinerarProgress"){
                popUpTo("SplashScreen") { inclusive =true }
            }
        }
    }
}
@Composable
fun LogoWithText(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box() {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "App Logo",
                modifier = Modifier.size(200.dp) // smaller for balance
            )

            // Optional: place text overlapping bottom of logo
            Text(
                text = "ReliefNet",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontFamily = inriaSerifFontFamily
                ),
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(top = 0.dp) // adjust as per logo size
            )
        }

       // Spacer(modifier = Modifier.height(8.dp))

        Text(modifier = Modifier,
            text = "Bridging Care, Compassion, and Connection",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontFamily = cantataOneFontFamily,
                fontSize = 14.sp
            ),
            color = Color.White
        )
    }
}

