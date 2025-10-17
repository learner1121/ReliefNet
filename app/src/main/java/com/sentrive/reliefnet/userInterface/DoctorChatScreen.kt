package com.sentrive.reliefnet.userInterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sentrive.reliefnet.R
import com.sentrive.reliefnet.ui.theme.poppinsFontFamily

@Composable
fun DoctorChatScreen(){

    Scaffold(
        topBar = {DoctorTopBar("Dr. Rahul Verma",true)},
        bottomBar = {
            DoctorChatBottomBar()
        },
        modifier = Modifier.statusBarsPadding()
            .navigationBarsPadding()
    ) { paddingValues ->
        Column(Modifier.fillMaxSize()
            .padding(paddingValues)) {
            DoctorChats()
        }
    }
}
@Composable
fun DoctorTopBar(doctorName: String,doctorStatus: Boolean){
    Row (Modifier
        .fillMaxWidth()
        .background(Color.White),
        verticalAlignment = Alignment.CenterVertically){
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(R.drawable.doc_back_arrow),
                contentDescription = "Back Arrow"
            )
        }
        Box(Modifier
            .size(49.dp, 45.dp)
            .clip(CircleShape)){
            Image(painter = painterResource(R.drawable.doc_image),
                contentDescription = "Doc Profile Pic",
                contentScale = ContentScale.Crop)
        }
        Spacer(Modifier.width(12.dp))

        var docStatus = if (doctorStatus) "Active Now" else "Offline"
        val color = if (doctorStatus) Color.Green else Color.LightGray
        Column(horizontalAlignment = Alignment.Start) {
            Row { Text(doctorName,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.W500,
                    fontSize = 18.sp,
                    color = Color.Black
                )) }
            Row(verticalAlignment = Alignment.CenterVertically) { Text(docStatus,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.W400,
                    fontSize = 10.sp,
                    color = Color.LightGray
                ))
            Spacer(Modifier.width(4.dp))
                Box(Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(color))
            }
        }
    Spacer(Modifier.weight(1f))
        IconButton(onClick = {}){
        Icon(
            painter = painterResource(R.drawable.doc_audio_call),
            contentDescription = "Video Call",
            modifier = Modifier.size(
                26.dp, 18.dp
            )
        )
    }
        Spacer(Modifier.width(20.dp))
       IconButton(onClick = {}) {
            Icon(
                painter = painterResource(R.drawable.doc_video_call),
                contentDescription = "Video Call",
                modifier = Modifier
                    .size(
                        26.dp, 18.dp
                    )
                    .padding(end = 4.dp)

            )
        }
    }
}

@Composable
fun DoctorChats(){
    val messages = remember {
        listOf(
            FakeMessage("Are you still travelling?", false, "10:02 AM"),
            FakeMessage("Yes, I’m in Pune....", true, "10:03 AM"),
            FakeMessage("OoOo, Thats so Cool!", false, "10:04 AM"),
            FakeMessage("Raining??", false, "10:04 AM"),
            FakeMessage("🎤 Voice message", true, "10:05 AM"),
            FakeMessage("Hi, Did you heard?", false, "Thursday 24, 2022 - 10:10 AM"),
            FakeMessage("🎤 Voice message", false, "10:11 AM"),
            FakeMessage("Ok!", false, "10:12 AM"),
            FakeMessage("🎤 Voice message", true, "10:13 AM")
        )
    }
    LazyColumn(
        modifier = Modifier.background(Color.White)

            //.fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(messages) { msg ->
            DoctorMessageBubble(msg)
        }
    }


}
@Composable
fun DoctorMessageBubble(message: FakeMessage){
    val bubbleColor = if (message.isMine) Color(0xFF8C8A9F) else Color.LightGray
    val textColor = if (message.isMine) Color.Black else Color(0xFF383737)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isMine) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ){
        // Receiver's profile pic
        if (!message.isMine) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(Color.DarkGray)
            ){
                Image(painterResource(R.drawable.doc_image),
                    contentDescription = "DoctorProfile",
                    contentScale = ContentScale.Crop)
            }
            Spacer(modifier = Modifier.width(6.dp))
        }
        // Message + timestamp row
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // For sender → time first, then bubble
            if (message.isMine) {
                Text(
                    text = message.time,
                    color = Color.Gray,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(bottom = 2.dp)

                )
            }

            // Chat bubble
            Box(
                modifier = Modifier
                    .widthIn(max = 260.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = if (message.isMine) 16.dp else 4.dp,
                            topEnd = if (message.isMine) 4.dp else 16.dp,
                            bottomStart = 16.dp,
                            bottomEnd = 16.dp
                        )
                    )
                    .background(bubbleColor)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = message.text,
                    color = textColor,
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                )
            }

            // For receiver → bubble first, then time
            if (!message.isMine) {
                Text(
                    text = message.time,
                    color = Color.LightGray,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
            }
        }
    }

}
@Composable
fun DoctorChatBottomBar(){
    var chat by remember { mutableStateOf("") }
   Row(Modifier.fillMaxWidth()
       .background(Color.White)
       .padding(all = 13.dp),
       verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            value = chat,
            onValueChange = { chat = it },
            placeholder = {
                Text(
                    "Send Message",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        color = Color.LightGray
                    )
                )
            },
            modifier = Modifier.background(Color.White),
            shape = RoundedCornerShape(16.dp),
            trailingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        painterResource(R.drawable.send_icon),
                        contentDescription = "Send",
                        Modifier.size(19.dp)
                    )
                }
            }
        )
       Spacer(Modifier.weight(1f))
       Box(Modifier.size(50.dp)
           .clip(CircleShape)

           .background(brush =
               Brush.linearGradient(
                   listOf(Color(0xFF49467E),
                       Color(0xFF007665),
                       Color(0xF22C2871)
                   )
               )


           ),
           contentAlignment = Alignment.Center){
           Icon(painterResource(R.drawable.mic_doc),
               contentDescription = "Mic",
               tint = Color.White)
       }
    }
}
@Preview(showSystemUi = true)
@Composable
fun show(){
   // DoctorTopBar("Gautam Sah",false)
    DoctorChatScreen()
}