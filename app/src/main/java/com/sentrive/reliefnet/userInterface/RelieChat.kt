package com.sentrive.reliefnet.userInterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sentrive.reliefnet.R
import com.sentrive.reliefnet.ui.theme.interFontFamily

data class FakeMessage(
    val text: String,
    val isMine: Boolean,
    val time: String
)

@Composable
fun RelieChat() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background image
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(Modifier.height(65.dp))
            // Top Bar
            RelieRow("Relie", R.drawable.arrow_2)

            // Chat Messages
            Chats()
            Spacer(Modifier.height(82.dp))
            MessageRow("Message...")

        }
    }
}

@Composable
fun RelieRow(text: String, backarrow: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        // Arrow (left aligned)
        Image(
            painter = painterResource(backarrow),
            contentDescription = "BackArrow",
            Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterStart)
        )

        // Centered title
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = interFontFamily
            ),
            color = Color.Black
        )
    }
}


@Composable
fun Chats() {
    val messages = remember {
        listOf(
            FakeMessage("This is the main chat template", true, "9:41 AM"),
            FakeMessage("Oh?", false, "9:42 AM"),
            FakeMessage("Cool", false, "9:42 AM"),
            FakeMessage("How does it work?", false, "9:43 AM"),
            FakeMessage("You just edit any text to type in the conversation you want to show, and delete any bubbles you don’t want to use", true, "9:44 AM"),
            FakeMessage("Boom!", true, "9:44 AM"),
            FakeMessage("Hmmm", false, "9:45 AM"),
            FakeMessage("I think I get it", false, "9:46 AM"),
            FakeMessage("Will head to the Help Center if I have more questions tho", false, "9:47 AM")
        )
    }

    LazyColumn(
        modifier = Modifier
            //.fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(messages) { msg ->
            MessageBubble(msg)
        }
    }

}

@Composable
fun MessageBubble(message: FakeMessage) {
    val bubbleColor = if (message.isMine) Color.Black else Color.White
    val textColor = if (message.isMine) Color.White else Color.Black

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isMine) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        // Receiver's profile pic
        if (!message.isMine) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(Color.DarkGray)
            ){
                Image(painterResource(R.drawable.doc_image),
                    contentDescription = "Relie Profile",
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
                    color = Color.LightGray,
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
                    lineHeight = 20.sp
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
fun MessageRow(placeHolder: String){
    var msg by remember { mutableStateOf("") }
    Box(modifier = Modifier
        .padding(start = 32.dp, end = 15.dp )){
        OutlinedTextField(
            value = msg,
            onValueChange = {msg = it},
            placeholder = {Text(placeHolder,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = interFontFamily,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    color = Color.Gray
                    
                ))},
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            ),
            trailingIcon = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(R.drawable.mic),contentDescription = "Mic",Modifier.size(24.dp))
                    Spacer(Modifier.width(16.dp))
                    Image(painter = painterResource(R.drawable.emoji),contentDescription = "Emoji",Modifier.size(24.dp))
                    Spacer(Modifier.width(16.dp))
                    Image(painter = painterResource(R.drawable.image),contentDescription = "Image",Modifier.size(24.dp))
                    Spacer(Modifier.width(8.dp))
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        )
    }
}
//@Preview(showBackground = true)
//@Composable
//fun PreviewRelieChat() {
//    RelieChat()
//}
//
//
//
//
@Preview
@Composable
fun s(){
    RelieChat()
}