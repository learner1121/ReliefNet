package com.sentrive.reliefnet.userInterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sentrive.reliefnet.R

@Composable
fun LinearProgress(navHostController: NavHostController){


    Box(Modifier.fillMaxSize()
        ){
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            Row(Modifier.width(134.dp)) {
                LinearProgressIndicator(
                    color = ProgressIndicatorDefaults.linearColor,
                    trackColor = ProgressIndicatorDefaults.linearTrackColor,
                    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
                )
            }
        }
        LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(2000)
            navHostController.navigate("LoginScreen"){
                popUpTo("LinerarProgress") { inclusive = true }
            }
        }

    }
}
//@Preview
//@Composable
//fun show(){
//   LoginScreen()
//}
