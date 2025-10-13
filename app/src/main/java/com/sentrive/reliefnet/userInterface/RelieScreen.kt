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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sentrive.reliefnet.R

@Composable
fun RelieScreen() {
    val message = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,

        )
        Spacer(Modifier.height(30.dp))
        Row (Modifier.fillMaxWidth()
            .height(72.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End){
            Image(
                painter = painterResource(R.drawable.menu),
                contentDescription = "Menu",
                modifier = Modifier.padding(end = 26.dp)
                    .size(26.dp)

            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hello there!!",
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "How can I help you Today?",
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = message.value,
                onValueChange = { message.value = it },
                placeholder = { Text("Message...") },
                trailingIcon = {
                    Row {
                        Image(painter = painterResource(R.drawable.mic),contentDescription = "Mic",Modifier.size(24.dp))
                        Spacer(Modifier.width(16.dp))
                        Image(painter = painterResource(R.drawable.emoji),contentDescription = "Emoji",Modifier.size(24.dp))
                        Spacer(Modifier.width(16.dp))
                        Image(painter = painterResource(R.drawable.image),contentDescription = "Image",Modifier.size(24.dp))
                        Spacer(Modifier.width(8.dp))
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp)
            )
        }

    }
}