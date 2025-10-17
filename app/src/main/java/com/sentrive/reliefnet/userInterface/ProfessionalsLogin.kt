package com.sentrive.reliefnet.userInterface

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sentrive.reliefnet.R
import com.sentrive.reliefnet.ui.theme.interFontFamily

@Composable
fun ProfessionalLoginScreen(){
    Box(Modifier.fillMaxSize()){
        Image(painter = painterResource(R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize())
        Column (Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally){
            LogoWithText("Healthcare Professional Portal")
            Spacer(Modifier.height(20.dp))
            ProfessionalsLoginColumn()
        }

    }
}
@Composable
fun ProfessionalsLoginColumn(){
    val doctorSpecializations = listOf(
        "Cardiologist",
        "Dermatologist",
        "Pediatrician",
        "Neurologist",
        "Orthopedic Surgeon"
    )
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var medicalId by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    var  selectedSpecialization="Selected Specialization"

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    var columnWidth =screenWidth - 20

    Column (Modifier.width(columnWidth.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Box(){

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "Doctor/Nurse Login",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                )
                Text(
                    "Access your drofessional dashboard",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        color = Color.LightGray
                    )
                )
                Spacer(Modifier.height(16.dp))
                Row {
                    OutlinedTextField(value = email,
                        onValueChange = {email = it},
                        placeholder = {Text("Professional Email",
                            style = MaterialTheme.typography.titleSmall.copy(

                                fontSize = 14.sp,
                                fontFamily = interFontFamily
                            ),
                            color = Color(0xFF9E9E9E))},
                        modifier = Modifier
                            .weight(1f),
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ))
                }
                Spacer(Modifier.height(12.dp))
                Row {
                    OutlinedTextField(value = password,
                        onValueChange = {password = it},
                        placeholder = {Text("Password",
                            style = MaterialTheme.typography.titleSmall.copy(

                                fontSize = 14.sp,
                                fontFamily = interFontFamily
                            ),
                            color = Color(0xFF9E9E9E))},
                        modifier = Modifier
                            .weight(1f),
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ))
                }
                Spacer(Modifier.height(12.dp))
                Row {
                    OutlinedTextField(value = medicalId,
                        onValueChange = {medicalId = it},
                        placeholder = {Text("Medical Id",
                            style = MaterialTheme.typography.titleSmall.copy(

                                fontSize = 14.sp,
                                fontFamily = interFontFamily
                            ),
                            color = Color(0xFF9E9E9E))},
                        modifier = Modifier

                            .weight(1f),
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        ))
                }
                Spacer(Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(16.dp)) // Apply clipping first
                        .background(Color.White) // Then apply background within the clipped area
                        .padding(horizontal = 16.dp), // Optional: Add horizontal padding
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        selectedSpecialization,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontSize = 14.sp,
                            fontFamily = interFontFamily
                        ),
                        color = Color(0xFF9E9E9E),
                        modifier = Modifier
                            .weight(1f)
                    )

                    Box(Modifier.size(24.dp)) {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                painter = painterResource(R.drawable.dropdown),
                                contentDescription = "Drop Down",
                                modifier = Modifier.size(24.dp),
                                tint = Color(0xFF9E9E9E)
                            )
                        }

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            doctorSpecializations.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        // Handle selection
                                        selectedSpecialization = option
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(Modifier.width(16.dp))
                }
                Spacer(Modifier.height(12.dp))
                OutlinedButton(onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFB6D4)
                    ),
                    modifier = Modifier.fillMaxWidth()) {
                    Text("Sign in",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.W500,
                            fontSize = 14.sp,
                            color = Color.Black
                        ))

                }
                Spacer(Modifier.height(20.dp))

                Row {
                    Text("Don't have an account? ",
                        fontSize = 14.sp,
                        color = Color.White)
                            Text("Register here",
                                color = Color.Magenta,
                                fontSize = 14.sp,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.clickable(onClick = {}))
                }
                Row {
                    Text("Forgot Password?",
                        fontSize = 14.sp,
                        color = Color.Magenta,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable(
                            onClick = {
                            }
                        ))
                }
                Row { Text("By signing in, you agree to our ",
                    fontSize = 14.sp,
                    color = Color.White)
                                    Text("Terms of Service",
                                        fontSize = 14.sp,
                                        color = Color.Magenta,
                                        textDecoration = TextDecoration.Underline,
                                        modifier = Modifier.clickable(
                                            onClick = {
                                            }
                                        ))
                }
                Row { Text("and ",
                    fontSize = 14.sp,
                    color = Color.White)
                                    Text("Privacy Policy",
                                        fontSize = 14.sp,
                                        color = Color.Magenta,
                                        textDecoration = TextDecoration.Underline,
                                        modifier = Modifier.clickable(
                                            onClick = {}
                                        ))
                }
            }
        }

    }
}
@Preview
@Composable
fun A(){
    ProfessionalLoginScreen()
}