package com.sentrive.reliefnet.userInterface

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.sentrive.reliefnet.R
import com.sentrive.reliefnet.auth.GoogleAuthActivity
import com.sentrive.reliefnet.ui.theme.interFontFamily

@Composable
fun LoginScreen(navHostController: NavHostController){
    val context = LocalContext.current

    // 🛡️ Ensure Firebase is initialized
    LaunchedEffect(Unit) {
        if (FirebaseApp.getApps(context).isEmpty()) {
            FirebaseApp.initializeApp(context)
        }
    }

    var email by remember { mutableStateOf("") }
    val loginSuccess = remember { mutableStateOf(false) }

    // 🛡️ Avoid direct call until initialization
    val currentUser = remember {
        try {
            FirebaseAuth.getInstance().currentUser
        } catch (e: IllegalStateException) {
            null
        }
    }

    if (currentUser != null) {
        LaunchedEffect(Unit) {
            navHostController.navigate("Home") {
                popUpTo("LoginScreen") { inclusive = true }
            }
        }
        return
    }

    // 2️⃣ Launcher for GoogleAuthActivity
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            loginSuccess.value = true
        }
    }
    LaunchedEffect(loginSuccess.value) {
        if (loginSuccess.value) {
            navHostController.navigate("Home") {
                popUpTo("LoginScreen") { inclusive = true }
            }
        }
    }

    Box(Modifier.fillMaxSize()
        ){
        Image(
            painter = painterResource(R.drawable.bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column (Modifier.fillMaxWidth()
            , horizontalAlignment = Alignment.CenterHorizontally){

            //Logo and Text function
            LogoWithText("Bridging Care, Compassion, and Connection")

            Column (Modifier
                .fillMaxWidth()
                .padding(top = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally){

                //Login Screen Upper Text
                Text("Create an Account",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = interFontFamily,
                    fontSize = 15.sp)
                Spacer(Modifier.height(8.dp))
                Text("Enter your Email to Sign Up for this App",
                    fontFamily = interFontFamily,
                    fontSize = 12.sp,
                    color = Color.White)
                Spacer(Modifier.height(16.dp))

                //Email Input TextField
                OutlinedTextField(value = email,
                    onValueChange = {email = it},
                    placeholder = {Text("email@domain.com",
                        fontFamily = interFontFamily,
                        fontSize = 15.sp,
                        color = Color.Gray)},
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Cyan,
                        unfocusedBorderColor = Color.White,
                        unfocusedContainerColor = Color.White,
                   ),shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .width(300.dp),
                )

                Spacer(Modifier.height(12.dp))

                //Continue Button
                val context = LocalContext.current
                Button(onClick = { navHostController.navigate("Home")},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Magenta
                    ),
                    modifier = Modifier.width(300.dp),
                    shape = RoundedCornerShape(10.dp)) {
                    Text("Continue",fontFamily = interFontFamily,)
                }

                Spacer(Modifier.height(16.dp))
                //Divider
               Row(Modifier.width(300.dp),
                   verticalAlignment = Alignment.CenterVertically) {
                    HorizontalDivider(Modifier.weight(1f),
                        thickness = 1.dp,
                        color = Color.Black
                    )
                   Text("or",
                       fontFamily = interFontFamily,
                       color = Color.Gray,
                       fontSize = 12.sp,
                       modifier = Modifier.padding(horizontal = 16.dp))
                   HorizontalDivider(Modifier.weight(1f),
                        thickness = 1.dp,
                        color = Color.Black
                    )
                }

                Spacer(Modifier.height(16.dp))
                //Google Login
                Button(onClick = {
                    val intent = Intent(context, GoogleAuthActivity::class.java)
                    launcher.launch(intent)
                },
                    modifier = Modifier.width(300.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black

                    )) {
                    Row(verticalAlignment = Alignment.CenterVertically,
                            ){
                        Image(
                            painter = painterResource(R.drawable.googlelogo),
                            contentDescription = "googleLogin",
                                    modifier = Modifier.height(30.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("Continue with Apple",fontFamily = interFontFamily,)
                    }
                }
                Spacer(Modifier.height(8.dp))
                //Apple Login
                Button(onClick = {},
                    modifier = Modifier.width(300.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.applelogo),
                            contentDescription = "googleLogin",
                            modifier = Modifier.height(30.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("Continue with Apple",fontFamily = interFontFamily,)
                    }
                }
            }
        }


        }
    }
