package com.sentrive.reliefnet.userInterface

import android.R.attr.onClick
import android.annotation.SuppressLint
import android.text.Layout
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imeAnimationTarget
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsIgnoringVisibility
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsIgnoringVisibility
import androidx.compose.foundation.layout.waterfall
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.annotations.concurrent.Background
import com.sentrive.reliefnet.R
import com.sentrive.reliefnet.ui.theme.alegreyaFontFamily
import com.sentrive.reliefnet.ui.theme.alegreyaSansFontFamily
import com.sentrive.reliefnet.ui.theme.inriaSerifFontFamily
import com.sentrive.reliefnet.ui.theme.interFontFamily
import com.sentrive.reliefnet.ui.theme.mitrFontFamily


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomePage(navHostController: NavHostController){

    //Calculating the actual width of the device then providing dynamic padding as required
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    //val bottomNavPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val searchBoxWidth = screenWidth -27
    val welcomeCardWidth  = screenWidth - 14
    val mentalHealthSupportCardWidth  = screenWidth -18
    val relieCardWidth = screenWidth - 33
    //Main Box
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 30.dp)
    )
    {
        //Background Image
        Image(painter = painterResource(R.drawable.bg),
            contentDescription = null,
            Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {TopBar(navHostController)},
            modifier =  Modifier,
            bottomBar = {MainBottomBar(navHostController)}
        ) { @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

            //White Background
           Box(
               Modifier
                   .then(Modifier.padding(top = 72.dp))
                   .then(Modifier.padding(bottom = 92.dp))
                   .background(Color.White)
                   .fillMaxSize(),
               contentAlignment = Alignment.TopCenter
           )
           {
               //Column to maintain content alignment and apply hierarchy betn the boxes
               Column(horizontalAlignment = Alignment.CenterHorizontally) {

                   //Search Card
                   SearchCard(0xFFF5F5F5,searchBoxWidth.dp)

                   //Welcome Card
                   WelcomeCard(0xFFFAD6FF,welcomeCardWidth.dp)

                   //RelieCard
                   RelieCard(navHostController,relieCardWidth.dp)


                   Row {
                       Spacer(Modifier.width(35.dp))
                       Text(
                           "Our Services",
                           style = MaterialTheme.typography.titleMedium.copy(
                               fontFamily = alegreyaFontFamily,
                               fontSize = 20.sp,
                               fontWeight = FontWeight.W500
                           ),
                          modifier =  Modifier
                              .padding(top = 15.dp)
                              .weight(1f)
                       )
                   }

                   //Mental Health Support Card
                   MentalHeatthSupportCard(navHostController,0xFFB39DDB,mentalHealthSupportCardWidth.dp)

                   //Booking Card
                   BookingMain(279.dp,269.dp,navHostController)
               }
           }

        }
    }
}

//Background Image
@Composable
fun BackgroungImage(){
    Image(painter = painterResource(R.drawable.bg),
        contentDescription = null,
        Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

}
@Composable
fun SearchCard(backgroundColor : Long, width: Dp){
    var searchText by remember { mutableStateOf("") }
    Spacer(Modifier.height(12.dp))
    Card(
        modifier = Modifier
            .width(width)
            .height(56.dp),
        shape = RoundedCornerShape(24.dp), // Rounded corners
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)) // Light grey background
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Icon",
                modifier = Modifier
                    .size(24.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        "Find Near by Home care nurses",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 15.sp,
                            fontFamily = interFontFamily
                        ),
                        color = Color.DarkGray
                    )
                },
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontFamily = interFontFamily
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                )
            )
        }
    }


}

//Welcome Card
@Composable
fun WelcomeCard(bgColor: Long,width: Dp){
    Box(Modifier.padding(top = 15.dp)) {
        Card (Modifier
            .size(width, 97.dp),
            colors = CardDefaults.cardColors(containerColor = Color(bgColor))){
            Column(Modifier.padding(start = 8.dp)){ Row() {Text("welcome to ReliefNet",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = alegreyaFontFamily
                )) }
                Text("Bridging Care, Compassion and ",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontFamily = alegreyaSansFontFamily,
                        fontWeight = FontWeight.Light,
                        fontSize = 15.sp
                    ))
                Text("connection",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontFamily = alegreyaSansFontFamily,
                        fontWeight = FontWeight.Light,
                        fontSize = 15.sp
                    ))
            }
        }
    }
}

//RelieCard
@Composable
fun RelieCard(navHostController: NavHostController,width: Dp){
    Box(Modifier
        .padding(top = 15.dp)
        .clickable(onClick = { navHostController.navigate("RelieScreen") })){
        Card(Modifier.size(width,70.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFB3D6))) {
            Row (verticalAlignment = Alignment.CenterVertically){
                Text("Relie",
                    Modifier
                        .padding(start = 35.dp)
                        .weight(1f),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = alegreyaFontFamily,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Medium
                    ))
                Image(painterResource(R.drawable.relie_icon),
                    contentDescription = "Relie Icon",
                    Modifier
                        .size(83.dp, 81.dp)
                        .padding(
                            end = 27.dp
                        ))
            }
        }
    }
}

//MentalHeatthSupportCard
@Composable
fun MentalHeatthSupportCard(navHostController: NavHostController,bgColor : Long,width: Dp){
    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(0.5.dp, Color(bgColor)), // Light purple border
        modifier = Modifier
            .size(width, 72.dp)
            .padding(top = 15.dp)
            .clickable { navHostController.navigate("MentalHealthSupport") },
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .height(100.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Mental Health Support",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = mitrFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 16.sp
                ), modifier = Modifier.weight(1f)
            )
            Icon(
                painterResource(R.drawable.on_click),
                contentDescription = "Go",
                tint = Color(0xFFB39DDB), // Match the border color
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

data class Booking(
    val title: String,
    val doctorName: String,
    val status: String,        // e.g., "Completed", "Upcoming"
    val dateTime: String,      // e.g., "May 5, 2025, 11:00 AM"
    val callType: String       // e.g., "Video Call", "In-person"
)
@Composable
fun BookingScreen(rowWidth: Dp,cardWidth:Dp,navHostController: NavHostController) {
    val dummyBookings = listOf(
        Booking(
            title = "Therapy Session",
            doctorName = "Dr. Priya Sharma",
            status = "Completed",
            dateTime = "May 5, 2025, 11:00 AM",
            callType = "Video Call"
        ),
        Booking(
            title = "Dental Checkup",
            doctorName = "Dr. Raj Kumar",
            status = "Upcoming",
            dateTime = "May 10, 2025, 2:00 PM",
            callType = "In-person"
        ),
        Booking(
            title = "Nutrition Consultation",
            doctorName = "Dr. Anjali Mehta",
            status = "Completed",
            dateTime = "April 28, 2025, 9:00 AM",
            callType = "Video Call"
        )
    )

    var selectedTab by remember { mutableStateOf("Past") }

    Row(Modifier.padding(top = 10.dp),horizontalArrangement = Arrangement.Start) {
        Text(
            text = "Your Bookings",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
        )
    }

    // Tabs
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF0F0F0)) // Light grey
            .padding(2.dp)
            .width(rowWidth)
    ) {
        listOf("Upcoming", "Past").forEach { tab ->
            val isSelected = selectedTab == tab
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { selectedTab = tab }
                    .background(if (isSelected) Color.White else Color(0xFFF0F0F0))
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tab,
                    color = if (isSelected) Color(0xFF9C27B0) else Color.Gray,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
    LazyColumn(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {
        dummyBookings.forEach { dummyBooking ->
            item {

            Spacer(modifier = Modifier.height(8.dp))

            Spacer(modifier = Modifier.height(8.dp))

            // Booking Card
            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.width(cardWidth)
                    .clickable(onClick = {navHostController.navigate("BookingScreen")})
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = dummyBooking.title,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = dummyBooking.doctorName,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                        // Status Badge
                        val color = if (dummyBooking.status == "Completed") Color.LightGray else Color.Green
                        Box(
                            modifier = Modifier
                                .background(color, RoundedCornerShape(12.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = dummyBooking.status,
                                color = Color.Black,
                                fontSize = 12.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Date and Video Call Info
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        //Icon(Icons.Default.CalendarToday, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(dummyBooking.dateTime, color = Color.Gray)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Icon(Icons.Default.Chat, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(dummyBooking.callType, color = Color.Gray)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Action Buttons
                    ActionButtons()
                }
            }
        }
    }
}
}

@Composable
fun BookingMain(rowWidth: Dp,cardWidth:Dp,navHostController: NavHostController){

        val dummyBookings = listOf(
            Booking(
                title = "Therapy Session",
                doctorName = "Dr. Priya Sharma",
                status = "Completed",
                dateTime = "May 5, 2025, 11:00 AM",
                callType = "Video Call"
            ),
            Booking(
                title = "Dental Checkup",
                doctorName = "Dr. Raj Kumar",
                status = "Upcoming",
                dateTime = "May 10, 2025, 2:00 PM",
                callType = "In-person"
            ),
            Booking(
                title = "Nutrition Consultation",
                doctorName = "Dr. Anjali Mehta",
                status = "Completed",
                dateTime = "April 28, 2025, 9:00 AM",
                callType = "Video Call"
            )
        )

        var selectedTab by remember { mutableStateOf("Past") }

        Row(Modifier.padding(top = 10.dp),horizontalArrangement = Arrangement.Start) {
            Text(
                text = "Your Bookings",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.Black
                )
            )
        }

        // Tabs
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFFF0F0F0)) // Light grey
                .padding(2.dp)
                .width(279.dp)
                .height(38.dp)
        ) {
            listOf("Upcoming", "Past").forEach { tab ->
                val isSelected = selectedTab == tab
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { selectedTab = tab }
                        .background(if (isSelected) Color.White else Color(0xFFF0F0F0))
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = tab,
                        color = if (isSelected) Color(0xFF9C27B0) else Color.Gray,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            }
        }
        LazyColumn(modifier = Modifier.padding(top = 10.dp)
            .height(206.dp)) {
            dummyBookings.forEach { dummyBooking ->
                item {

                    Spacer(modifier = Modifier.height(8.dp))

                    // Booking Card
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier.width(cardWidth)
                            .clickable(onClick = {navHostController.navigate("BookingScreen")})
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = dummyBooking.title,
                                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp)
                                    )
                                    Text(
                                        text = dummyBooking.doctorName,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                // Status Badge
                                val color = if (dummyBooking.status == "Completed") Color.LightGray else Color.Green
                                Box(
                                    modifier = Modifier
                                        .background(color, RoundedCornerShape(12.dp))
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = dummyBooking.status,
                                        color = Color.Black,
                                        fontSize = 8.sp
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(2.dp))

                            // Date and Video Call Info
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                //Icon(Icons.Default.CalendarToday, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    painterResource(R.drawable.calender),
                                    contentDescription = "Date",
                                    modifier = Modifier.size(16.dp),
                                    tint = Color.Gray
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(dummyBooking.dateTime,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = Color.Gray,
                                        fontSize = 8.sp
                                    ))
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(
                                    painterResource(R.drawable.message),
                                    contentDescription = "Call Type",
                                    modifier = Modifier.size(16.dp),
                                    tint = Color.Gray
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(dummyBooking.callType,
                                        style = MaterialTheme.typography.titleSmall.copy(
                                        color = Color.Gray,
                                    fontSize = 8.sp
                                ))
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Action Buttons
                            ActionButtons()
                        }
                    }
                }
            }
        }
}
@Composable
fun ActionButtons(){
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedButton(
            onClick = { /* Book again */ },
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF9C27B0))
        ) {
            Text("Book Again",
                fontSize = 10.sp)
        }
        Button(
            onClick = { /* Leave review */ },
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
        ) {
            Text("Leave Review", color = Color.Black,
                fontSize = 10.sp)
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun DoctorsScreenPreview() {
//    HomePage()
//}