package com.sentrive.reliefnet.userInterface

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sentrive.reliefnet.R
import com.sentrive.reliefnet.ui.theme.alegreyaSansFontFamily
import com.sentrive.reliefnet.ui.theme.inriaSerifFontFamily
import com.sentrive.reliefnet.ui.theme.mitrFontFamily

@Composable
fun ProfileScreen(navHostController: NavHostController){

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val profileCardWidth = screenWidth - 10
    val cardsWidth  = screenWidth - 16
    Box(Modifier.fillMaxSize()
        .padding(top = 30.dp)){
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
                MainBottomBar(navHostController)
            },

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
                    Card(Modifier
                        .padding(top = 33.dp)
                        .size(profileCardWidth.dp, 94.8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFB3D6)
                        )) {
                        Row(Modifier.fillMaxSize()
                            .padding(start = 10.dp),
                            verticalAlignment = Alignment.CenterVertically){
                            Column(verticalArrangement = Arrangement.Center) {
                               Box(Modifier.size(61.dp)
                                   .clip(CircleShape)
                                   ) {
                                    Image(
                                        painter = painterResource(R.drawable.profile_pic),
                                        contentDescription = "User Pic",
                                        Modifier.size(61.dp),
                                        contentScale = ContentScale.Fit
                                    )
                                }
                                }
                            Column(modifier = Modifier.padding(start = 22.8.dp)) {
                                Text("Anjali Singh",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontSize = 20.sp,
                                        fontFamily = inriaSerifFontFamily,
                                        fontWeight = FontWeight.W400,
                                        color = Color.Black
                                    ))
                                Text("Mumbai, Maharashtra",style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 15.sp,
                                    fontFamily = alegreyaSansFontFamily,
                                    fontWeight = FontWeight.W300,
                                    color = Color.Black
                                )

                                    )
                            }
                        }
                    }
                    val info = listOf("Personal Information","Payment History","Your Bookings","Help & Support")
                    Column(Modifier.padding(top = 31.2.dp)) {
                    info.forEach { i->
                        Cards(i,cardsWidth.dp)
                        Spacer(Modifier.height(6.dp))
                    }
                    }
                }
            }
        }
    }
}
@Composable
fun TopBar(navHostController: NavHostController){
    Row (Modifier
        .height(72.dp)
        .padding(end = 16.dp),
        verticalAlignment = Alignment.CenterVertically){
       Box() {
            Text(
                "ReliefNet",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = inriaSerifFontFamily,
                    //fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    color = Color.White,
                ),
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }
        Spacer(Modifier.weight(1f))
        //Profile Picture
        Box(
            Modifier
                .size(36.dp)
                .clip(CircleShape)
                .clickable(onClick = {navHostController.navigate("ProfileScreen"){
                    launchSingleTop = true
                } }),

        ) {

            Image(
                painterResource(R.drawable.profile_pic),
                contentDescription = "Profile Pic",
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

@Composable
fun Cards(text: String, width: Dp){
    Card(Modifier.size(width,72.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(0.5.dp, Color(0xFFB39DDB)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )){
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 15.dp,end=27.17.dp)
                .fillMaxSize()){
                Text(text,
                    style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp,
                    fontFamily = mitrFontFamily,
                    fontWeight = FontWeight.W500,
                        color = Color.Black)
                )
            Spacer(Modifier.weight(1f))
            IconButton(onClick = {}) {
                Icon(painterResource(R.drawable.on_click),
                    contentDescription = "Go",
                    tint = Color(0xFFB39DDB),
                    modifier = Modifier.size(8.dp))
            }
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun t(){
//    ProfileScreen()
//}