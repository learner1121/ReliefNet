package com.sentrive.reliefnet.userInterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sentrive.reliefnet.R
import com.sentrive.reliefnet.ui.theme.alegreyaSansFontFamily
import com.sentrive.reliefnet.ui.theme.inriaSerifFontFamily

@Composable
fun BookingScreen1(navHostController: NavHostController){
    Box(Modifier.fillMaxSize()){
        Image(painterResource(R.drawable.bg),
            contentDescription = null,
            Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)

        Scaffold(
            topBar = {
                TopBar(navHostController)
            },
            containerColor = Color.Transparent,
            contentColor = Color.White,
            bottomBar = {
                MainBottomBar(navHostController
                )
            }
            // modifier = Modifier.background(Color.Transparent)
        ) { paddingValues ->
            Box(){
                Column(
                    Modifier
                        .padding(paddingValues)
                        .background(Color.White)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                   BookingScreen(374.dp,374.dp,navHostController)
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun s(){
//    BookingScreen1()
//}